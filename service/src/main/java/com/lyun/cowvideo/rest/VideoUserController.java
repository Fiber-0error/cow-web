package com.lyun.cowvideo.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoUpdateInfoParam;
import com.lyun.cowvideo.entity.param.VideoUploadParam;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.service.VideoInfoService;
import com.lyun.cowvideo.util.AccountInfoContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video/user")
@Api(tags = "用户操作视频")
public class VideoUserController {

    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping("/upload")
    @ApiOperation("上传视频")
    public Result uploadVideo(VideoUploadParam param) {
        return videoInfoService.uploadVideo(param);
    }

    @RequestMapping("/remove")
    @ApiOperation("删除视频")
    public Result removeVideo(int videoId){
        return videoInfoService.userRemoveVideo(videoId);
    }

    @RequestMapping("/update")
    @ApiOperation("更新视频信息")
    public Result updateVideoInfo(@RequestBody VideoUpdateInfoParam param){
        return videoInfoService.userUpdateVideoInfo(param);
    }

    @RequestMapping("/list")
    @ApiOperation("获取用户自己上传的视频")
    public Result listMyVideoList(int current, int size){
        Page<VideoInfoPO> page = new Page<>(current, size);
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null){
            return Result.fail("请先登录");
        }
        int accountId = accountInfo.getId();
        videoInfoService.getBaseMapper().selectPage(
                page,
                new LambdaQueryWrapper<VideoInfoPO>()
                        .eq(VideoInfoPO::getUploadUser, accountId)
        );
        return Result.success(page);
    }

}
