package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends IService<AccountPO> {

    Result doAuth(String username, String password);

    Result doRegister(String username, String password);

}
