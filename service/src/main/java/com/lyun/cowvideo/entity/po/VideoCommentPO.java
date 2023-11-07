package com.lyun.cowvideo.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("video_comment")
public class VideoCommentPO {

    private int id;

    private int videoId;

    private int parentComment;

    private int accountId;

    private String content;

    private String createdTime;

    private int delFlag;
}
