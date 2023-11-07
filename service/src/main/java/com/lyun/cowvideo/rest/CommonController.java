package com.lyun.cowvideo.rest;

import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
@CrossOrigin
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping("/video/upload")
    @ApiOperation("上传视频接口")
    public Result uploadVideo(MultipartFile file){
        return commonService.uploadVideo(file);
    }

    @RequestMapping("/img/upload")
    public Result uploadImg(MultipartFile file){
        return commonService.uploadImg(file);
    }
}
