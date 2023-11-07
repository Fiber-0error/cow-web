package com.lyun.cowvideo.service.impl;

import com.lyun.cowvideo.entity.CowUploadDo;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.service.CommonService;
import com.lyun.cowvideo.util.CowObjectStorageUtil;
import com.lyun.cowvideo.util.FileUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class CommonServiceImpl implements CommonService {

    @SneakyThrows
    @Override
    public Result uploadVideo(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null){
            return Result.fail("文件名不能为空");
        }
        if (!FileUtil.filterVideo(originalFilename)){
            return Result.fail("非法的视频格式");
        }
        InputStream inputStream = file.getInputStream();
        CowUploadDo cowUploadDo = CowObjectStorageUtil.upload(inputStream);
        if (cowUploadDo == null){
            return Result.fail("上传失败");
        }
        return Result.success(cowUploadDo);
    }

    @SneakyThrows
    @Override
    public Result uploadImg(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null){
            return Result.fail("文件名不能为空");
        }
        if (!FileUtil.filterImg(originalFilename)){
            return Result.fail("非法的视频格式");
        }
        InputStream inputStream = file.getInputStream();
        CowUploadDo cowUploadDo = CowObjectStorageUtil.upload(inputStream);
        if (cowUploadDo == null){
            return Result.fail("上传失败");
        }
        return Result.success(cowUploadDo);
    }
}
