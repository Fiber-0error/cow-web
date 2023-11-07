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
@TableName("like_info")
public class LikeInfoPO {

    private int id;

    /**
     * 账户id
     */
    private int accountId;

    /**
     * 视频id
     */
    private int videoId;

    /**
     * 点赞时间
     */
    private String updateTime;
}
