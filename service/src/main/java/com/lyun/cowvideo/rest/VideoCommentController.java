package com.lyun.cowvideo.rest;

import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoCommentParam;
import com.lyun.cowvideo.service.VideoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video/comment")
@Api(tags = "视频评论")
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @PostMapping("/")
    @ApiOperation("评论视频")
    public Result commentVideo(@RequestBody VideoCommentParam param){
        return videoCommentService.comment(param);
    }


    @GetMapping("/list")
    @ApiOperation("获取视频的评论")
    public Result commentList(int videoId){
        return videoCommentService.commentList(videoId);
    }

}
