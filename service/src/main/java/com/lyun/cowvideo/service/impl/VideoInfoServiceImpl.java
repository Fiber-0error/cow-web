package com.lyun.cowvideo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.*;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoUpdateInfoParam;
import com.lyun.cowvideo.entity.param.VideoUploadParam;
import com.lyun.cowvideo.entity.po.*;
import com.lyun.cowvideo.entity.vo.VideoListVo;
import com.lyun.cowvideo.service.LikeStatusService;
import com.lyun.cowvideo.service.VideoInfoService;
import com.lyun.cowvideo.util.AccountInfoContext;
import com.lyun.cowvideo.util.TimeUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfoPO> implements VideoInfoService {

    private final LikeInfoMapper likeInfoMapper;

    private final VideoTypeMapper videoTypeMapper;

    private final WatchHistoryMapper watchHistoryMapper;

    @Lazy
    private final LikeStatusService likeStatusService;

    @Autowired
    private CollectMapper collectMapper;

    public VideoInfoServiceImpl(VideoTypeMapper videoTypeMapper, WatchHistoryMapper watchHistoryMapper, LikeInfoMapper likeInfoMapper, LikeStatusService likeStatusService) {
        this.videoTypeMapper = videoTypeMapper;
        this.watchHistoryMapper = watchHistoryMapper;
        this.likeInfoMapper = likeInfoMapper;
        this.likeStatusService = likeStatusService;
    }

    @Override
    public Result uploadVideo(VideoUploadParam param) {
        String name = param.getName();
        String describe = param.getDescribe();
        String type = param.getType();
        String url = param.getUrl();
        String[] tag = param.getTag();
        String imgUrl = param.getImgUrl();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(type)) {
            return Result.fail("缺少参数");
        }
        VideoTypePO videoTypePO = videoTypeMapper.selectOne(
                new LambdaQueryWrapper<VideoTypePO>()
                        .eq(VideoTypePO::getName, type)
        );
        if (videoTypePO == null) {
            return Result.fail("未知的类型");
        }
        StringBuilder tagBuilder = new StringBuilder();
        if (tag != null) {
            for (String t : tag) {
                tagBuilder.append(t).append(",");
            }
            if (tagBuilder.length() > 0) {
                // 删除最后一个,
                tagBuilder.deleteCharAt(tagBuilder.length() - 1);
            }
        }
        VideoInfoPO videoInfoPO = VideoInfoPO.builder()
                .delFlag(0)
                .updateTime(new Timestamp(System.currentTimeMillis()).toString())
                .createdTime(new Timestamp(System.currentTimeMillis()).toString())
                .url(url).describe(describe).name(name).tag(tagBuilder.toString())
                .imgUrl(imgUrl)
                .build();
        this.save(videoInfoPO);
        return Result.success();
    }

    @Override
    public Result swapperNext() {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        List<Integer> videoIds = new ArrayList<>();
        if (accountInfo != null) {
            int accountId = accountInfo.getId();
            videoIds = watchHistoryMapper.selectList(
                    new LambdaQueryWrapper<WatchHistoryPO>()
                            .eq(WatchHistoryPO::getAccountId, accountId)
            ).stream().map(WatchHistoryPO::getVideoId).collect(Collectors.toList());
        }
        if (videoIds.isEmpty()) videoIds = null;
        List<VideoInfoPO> videoInfoPOList = this.baseMapper.nextMostViewAndUserNotWatch(videoIds, 5);
        if (videoInfoPOList.isEmpty()) {
            videoInfoPOList = randomWithoutWatch(accountInfo.getId(), 5);
        }
        return Result.success(videoInfoList2Vo(videoInfoPOList));
    }

    @Override
    public Result doWatch(int videoId) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) {
            return Result.fail("未知的视频");
        }
        if (accountInfo != null) {
            // 如果不是游客登录
            int accountId = accountInfo.getId();
            // 记录视频观看记录
            WatchHistoryPO watchHistoryPO = watchHistoryMapper.selectOne(
                    new LambdaQueryWrapper<WatchHistoryPO>()
                            .eq(WatchHistoryPO::getAccountId, accountId)
                            .eq(WatchHistoryPO::getVideoId, videoId)
            );
            if (watchHistoryPO == null) {
                WatchHistoryPO newWatchHistory = WatchHistoryPO.builder()
                        .watchTime(new Timestamp(System.currentTimeMillis()).toString())
                        .accountId(accountId)
                        .videoId(videoId)
                        .watchProgress("00:00:00")
                        .build();
                watchHistoryMapper.insert(newWatchHistory);
            } else {
                watchHistoryPO.setWatchTime(new Timestamp(System.currentTimeMillis()).toString());
                watchHistoryMapper.updateById(watchHistoryPO);
                videoInfoPO.setWatchProgress(watchHistoryPO.getWatchProgress());
            }
            // 获取当前用户是否对该视频点赞
            LikeInfoPO likeInfoPO = likeInfoMapper.selectOne(
                    new LambdaQueryWrapper<LikeInfoPO>()
                            .eq(LikeInfoPO::getVideoId, videoId)
                            .eq(LikeInfoPO::getAccountId, accountId)
            );
            videoInfoPO.setIsLike(likeInfoPO != null);
            // 获取当前用户是否对视频点赞

            CollectPO collectPO = collectMapper.selectOne(
                    new LambdaQueryWrapper<CollectPO>()
                            .eq(CollectPO::getVideoId, videoId)
                            .eq(CollectPO::getAccountId, accountId)
            );
            videoInfoPO.setIsCollect(collectPO != null);
        }
        // 设置视频点赞数
        videoInfoPO.setLikeCount(getVideoLikeCount(videoId));
        // 设置视频收藏数
        videoInfoPO.setCollectCount(getVideoCollectCount(videoId));
        return Result.success(videoInfoPO);
    }

    @Override
    public Result flashWatchProgress(int videoId, long watchProgress) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null) {
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();
        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) {
            return Result.fail("未知的视频");
        }
        WatchHistoryPO watchHistoryPO = watchHistoryMapper.selectOne(
                new LambdaQueryWrapper<WatchHistoryPO>()
                        .eq(WatchHistoryPO::getAccountId, accountId)
                        .eq(WatchHistoryPO::getVideoId, videoId)
        );
        if (watchHistoryPO == null) {
            return Result.fail("用户未观看过该视频");
        }
        watchHistoryPO.setWatchProgress(TimeUtil.second2Time(watchProgress));
        watchHistoryMapper.updateById(watchHistoryPO);
        // 用户喜好标签
        long totalTime = videoInfoPO.getTotalTime();
        float percent = (float) watchProgress / (float) totalTime; // 观看百分比
        if (percent > 0.3) {
            likeStatusService.increase(accountId, videoId);
        }
        return Result.success();
    }

    @Override
    public long getVideoLikeCount(int videoId) {
        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) return -1;
        return likeInfoMapper.selectCount(
                new LambdaQueryWrapper<LikeInfoPO>()
                        .eq(LikeInfoPO::getVideoId, videoId)
        );
    }

    @Override
    public long getVideoCollectCount(int videoId) {
        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) return -1;
        return collectMapper.selectCount(
                new LambdaQueryWrapper<CollectPO>()
                        .eq(CollectPO::getVideoId, videoId)
        );

    }

    @Override
    public Result recommendVideoByType(String type, int size) {

        AccountPO accountInfo = AccountInfoContext.getInfo();
        int accountId = accountInfo.getId();

        // 喜好部分的大小
        int likePartSize = (int) ((float) size * 0.5);
        // 热门部分大小
        int hotPartSize = size - likePartSize;


        List<VideoInfoPO> resList = new ArrayList<>();


        List<String> typeList;

        // 如果指定type则不需要获取用户喜好的type
        if (StringUtils.isNotBlank(type)) {
            typeList = new ArrayList<>();
            typeList.add(type);
        } else {
            typeList = likeStatusService.topType(accountId, likePartSize);
        }
        List<String> tagList = likeStatusService.topTag(accountId, likePartSize);

        List<Integer> watchedIds = watchHistoryMapper.selectList(
                new LambdaQueryWrapper<WatchHistoryPO>()
                        .eq(WatchHistoryPO::getAccountId, accountId)
        ).stream().map(WatchHistoryPO::getVideoId).collect(Collectors.toList());
        resList.addAll(this.baseMapper.queryRandomInTypeAndTagWithoutIds(typeList, tagList, watchedIds, likePartSize));


        resList.addAll(this.baseMapper.nextMostViewAndUserNotWatch(watchedIds, hotPartSize));

        resList.addAll(randomWithoutWatch(accountId, size / 2));

        return Result.success(videoInfoList2Vo(resList));
    }

    @Override
    public Result searchVideo(String keyWord, Page<VideoInfoPO> page, String type) {
        this.baseMapper.selectPage(page,
                new LambdaQueryWrapper<VideoInfoPO>()
                        .like(StringUtils.isNotBlank(keyWord), VideoInfoPO::getName, keyWord)
                        .eq(StringUtils.isNotBlank(type), VideoInfoPO::getType, type)
        );
        return Result.success(page);
    }

    @Override
    public Result userRemoveVideo(int videoId) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null) {
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();
        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) {
            return Result.fail("未知的视频");
        }

        if (videoInfoPO.getUploadUser() != accountId) {
            return Result.fail("只能删除自己的视频");
        }

        videoInfoPO.setDelFlag(1);
        this.baseMapper.updateById(videoInfoPO);
        return Result.success();
    }

    @Override
    public Result userUpdateVideoInfo(VideoUpdateInfoParam param) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null) {
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();

        int videoId = param.getId();

        VideoInfoPO videoInfoPO = this.baseMapper.selectById(videoId);
        if (videoInfoPO == null) {
            return Result.fail("未知的视频");
        }

        if (videoInfoPO.getUploadUser() != accountId) {
            return Result.fail("只能修改自己的视频");
        }
        videoInfoPO.setName(param.getVideoName());
        videoInfoPO.setDescribe(param.getDescribe());
        this.baseMapper.updateById(videoInfoPO);
        return Result.success();
    }

    @Override
    public Result hotKeyWord(int len) {
        return Result.success(this.baseMapper.hotKeyWord(len));
    }

    private List<VideoListVo> videoInfoList2Vo(List<VideoInfoPO> videoInfoPOList) {
        List<VideoListVo> resList = new ArrayList<>();
        for (VideoInfoPO po : videoInfoPOList) {
            VideoListVo vo = VideoListVo.builder()
                    .id(po.getId())
                    .uploadUser(po.getUploadUser())
                    .totalTime(po.getTotalTime())
                    .tag(po.getTag())
                    .type(po.getType())
                    .url(po.getUrl())
                    .name(po.getName())
                    .build();
            resList.add(vo);
        }
        return resList;
    }

    private List<VideoInfoPO> randomWithoutWatch(Integer accountId, int len) {
        List<Integer> videoIds = watchHistoryMapper.selectList(
                new LambdaQueryWrapper<WatchHistoryPO>()
                        .eq(WatchHistoryPO::getAccountId, accountId)
        ).stream().map(WatchHistoryPO::getVideoId).collect(Collectors.toList());
        return this.baseMapper.selectList(
                new LambdaQueryWrapper<VideoInfoPO>()
                        .notIn(VideoInfoPO::getId, videoIds)
                        .last("ORDER BY RAND() LIMIT " + len)
        );
    }
}
