package com.lyun.cowvideo.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.AccountMapper;
import com.lyun.cowvideo.dao.VideoCommentMapper;
import com.lyun.cowvideo.dao.VideoInfoMapper;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoCommentParam;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.VideoCommentPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.entity.vo.VideoCommentVo;
import com.lyun.cowvideo.service.VideoCommentService;
import com.lyun.cowvideo.util.AccountInfoContext;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoCommentPO> implements VideoCommentService {

    private final VideoInfoMapper videoInfoMapper;

    private final AccountMapper accountMapper;

    public VideoCommentServiceImpl(VideoInfoMapper videoInfoMapper, AccountMapper accountMapper) {
        this.videoInfoMapper = videoInfoMapper;
        this.accountMapper = accountMapper;
    }


    @Override
    public Result comment(VideoCommentParam param) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null){
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();
        int videoId = param.getVideoId();
        Integer parentId = param.getParentId();
        String content = param.getContent();
        VideoInfoPO videoInfoPO = videoInfoMapper.selectById(videoId);
        if (videoInfoPO == null){
            return Result.fail("未知的视频");
        }

        if (StringUtils.isBlank(content)){
            return Result.fail("评论内容不能为空");
        }

        if (parentId != null){
            VideoCommentPO parentComment = this.baseMapper.selectOne(
                    new LambdaQueryWrapper<VideoCommentPO>()
                            .eq(VideoCommentPO::getVideoId, videoId)
                            .eq(VideoCommentPO::getId, parentId)
                            .eq(VideoCommentPO::getDelFlag, 0)
            );
            if (parentComment == null){
                return Result.fail("未知的父评论");
            }
        }

        VideoCommentPO newComment = VideoCommentPO.builder()
                .videoId(videoId)
                .content(content)
                .accountId(accountId)
                .createdTime(new Timestamp(System.currentTimeMillis()).toString())
                .delFlag(0)
                .build();
        if (parentId != null){
            newComment.setParentComment(parentId);
        }
        this.baseMapper.insert(newComment);
        return Result.success();
    }

    @Override
    public Result commentList(int videoId) {
        VideoInfoPO videoInfoPO = videoInfoMapper.selectById(videoId);
        if (videoInfoPO == null){
            return Result.fail("未知的视频");
        }
        List<VideoCommentVo> resList = new ArrayList<>();

        // 获取第一级评论
        List<VideoCommentPO> rootComments = this.baseMapper.selectList(
                new LambdaQueryWrapper<VideoCommentPO>()
                        .eq(VideoCommentPO::getVideoId, videoId)
                        .isNull(VideoCommentPO::getParentComment)
                        .eq(VideoCommentPO::getDelFlag, 0)
        );

        commentPO2Vo(resList, rootComments);

        return Result.success(resList);
    }

    private List<VideoCommentVo> getSubCommentList(int parentId){
        List<VideoCommentVo> voList = new ArrayList<>();
        List<VideoCommentPO> rootComments = this.baseMapper.selectList(
                new LambdaQueryWrapper<VideoCommentPO>()
                        .eq(VideoCommentPO::getParentComment, parentId)
                        .eq(VideoCommentPO::getDelFlag, 0)
        );

        commentPO2Vo(voList, rootComments);
        return voList;
    }

    private void commentPO2Vo(List<VideoCommentVo> voList, List<VideoCommentPO> rootComments) {
        for (VideoCommentPO rootComment : rootComments) {
            AccountPO accountPO = accountMapper.selectById(rootComment.getAccountId());
            String name = null;
            if (accountPO != null){
                name = accountPO.getName();
            }
            VideoCommentVo vo = VideoCommentVo.builder()
                    .commentId(rootComment.getId())
                    .commentUser(name)
                    .commentTime(rootComment.getCreatedTime())
                    .content(rootComment.getContent())
                    .subCommentList(getSubCommentList(rootComment.getId()))
                    .build();
            voList.add(vo);
        }
    }
}
