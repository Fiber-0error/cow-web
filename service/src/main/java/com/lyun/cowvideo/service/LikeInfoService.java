package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.LikeInfoPO;
import org.springframework.stereotype.Service;

@Service
public interface LikeInfoService extends IService<LikeInfoPO> {

    Result like(int videoId, int accountId);

    Result getLikeList();
}
