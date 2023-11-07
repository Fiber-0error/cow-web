package com.lyun.cowvideo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoCommentVo {

    private int commentId;

    private String content;

    private String commentUser;

    private String commentTime;

    private List<VideoCommentVo> subCommentList;

}
