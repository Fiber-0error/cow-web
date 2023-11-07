package com.lyun.cowvideo.service;

import com.lyun.cowvideo.entity.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CommonService {

    Result uploadVideo(MultipartFile file);

    Result uploadImg(MultipartFile file);
}
