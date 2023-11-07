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
@TableName("watch_history")
public class WatchHistoryPO {

    private int id;
    /**
     * 视频id
     */
    private int videoId;
    /**
     * 账户id
     */
    private int accountId;
    /**
     * 观看时间
     */
    private String watchTime;
    /**
     * 观看进度
     */
    private String watchProgress;

}
