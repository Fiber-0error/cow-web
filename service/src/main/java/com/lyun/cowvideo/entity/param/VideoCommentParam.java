package com.lyun.cowvideo.entity.param;

import lombok.Data;

@Data
public class VideoCommentParam {

    private int videoId;

    private Integer parentId;

    private String content;
}
