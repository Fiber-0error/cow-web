package com.lyun.cowvideo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoListVo {

    private int id;

    private String name;

    private String tag;

    private String type;

    private long totalTime;

    private int uploadUser;

    private String url;
}
