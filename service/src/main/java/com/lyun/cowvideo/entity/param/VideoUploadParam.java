package com.lyun.cowvideo.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传视频参数类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoUploadParam {

    private String name;

    private String describe;

    private String[] tag;

    private String type;

    private String url;

    private String imgUrl;
}
