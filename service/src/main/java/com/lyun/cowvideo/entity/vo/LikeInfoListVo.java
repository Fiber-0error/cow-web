package com.lyun.cowvideo.entity.vo;

import com.lyun.cowvideo.entity.po.VideoInfoPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeInfoListVo {

    private int id;

    private VideoInfoPO videoInfoPO;

}
