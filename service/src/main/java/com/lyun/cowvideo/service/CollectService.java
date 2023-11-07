package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.CollectPO;
import org.springframework.stereotype.Service;

@Service
public interface CollectService extends IService<CollectPO> {

    Result collect(int videoId);

    Result collectList();
}
