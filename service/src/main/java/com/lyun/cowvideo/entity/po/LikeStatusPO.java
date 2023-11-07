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
@TableName("like_status")
public class LikeStatusPO {

    private int id;

    /**
     * 类型 1-type 2-tag
     */
    private int type;

    /**
     * 名称
     */
    private String name;

    /**
     * 账户id
     */
    private int accountId;

    /**
     * 统计数
     */
    private int count;

}
