package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.po.LikeStatusPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeStatusService extends IService<LikeStatusPO> {

    boolean increase(int accountId, int videoId);

    boolean decrease(int accountId, int videoId);

    List<String> topType(int accountId, int len);

    List<String> topTag(int accountId, int len);

}
