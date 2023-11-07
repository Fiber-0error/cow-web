package com.lyun.cowvideo.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoUploadParam;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.service.CollectService;
import com.lyun.cowvideo.service.LikeInfoService;
import com.lyun.cowvideo.service.VideoInfoService;
import com.lyun.cowvideo.util.AccountInfoContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频前端控制层类
 */
@RestController
@CrossOrigin
@Api(tags = "视频接口")
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private LikeInfoService likeInfoService;

    @Autowired
    private CollectService collectService;

    @RequestMapping("/upload")
    @ApiOperation("上传视频")
    public Result uploadVideo(VideoUploadParam param) {
        return videoInfoService.uploadVideo(param);
    }

    @ApiOperation("下滑视频获取列表")
    @RequestMapping("/swapper/next")
    public Result swapperNext() {
        return videoInfoService.swapperNext();
    }

    @RequestMapping("/watch")
    @ApiOperation("用户观看视频开始时调用该接口")
    public Result doWatch(int videoId) {
        return videoInfoService.doWatch(videoId);
    }

    @RequestMapping("/flash/watchProgress")
    @ApiOperation("更新观看进度")
    public Result flashWatchProgress(int videoId, long watchProgress) {
        return videoInfoService.flashWatchProgress(videoId, watchProgress);
    }

    @RequestMapping("/like")
    @ApiOperation("点赞")
    public Result likeVideo(int videoId) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null) {
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();
        return likeInfoService.like(videoId, accountId);
    }

    @RequestMapping("/recommend")
    @ApiOperation("根据类型获取视频推荐")
    public Result recommendVideoByType(String type, int size) {
        return videoInfoService.recommendVideoByType(type, size);
    }

    @RequestMapping("/collect")
    @ApiOperation("收藏视频")
    public Result collectVideo(int videoId) {
        return collectService.collect(videoId);
    }

    @RequestMapping("/search")
    @ApiOperation("搜索视频")
    public Result searchVideo(String keyWord, Page<VideoInfoPO> page, String type) {
        return videoInfoService.searchVideo(keyWord, page, type);
    }

    @RequestMapping("/hotKeyWord")
    @ApiOperation("获取热门视频关键字")
    public Result hotSearchKeyWord(int len){
        return videoInfoService.hotKeyWord(len);
    }

}
