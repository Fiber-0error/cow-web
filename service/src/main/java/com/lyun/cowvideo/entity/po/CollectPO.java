package com.lyun.cowvideo.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("collect")
public class CollectPO {

    private int id;

    private int accountId;

    private int videoId;

    private String createdTime;
}
