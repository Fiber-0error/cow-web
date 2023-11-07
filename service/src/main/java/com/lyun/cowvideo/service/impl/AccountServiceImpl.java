package com.lyun.cowvideo.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.AccountMapper;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.service.AccountService;
import com.lyun.cowvideo.util.JwtUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountPO> implements AccountService {


    @Override
    public Result doAuth(String username, String password) {
        String pwd = DigestUtil.md5Hex(password);
        AccountPO accountPO = baseMapper.selectOne(
                new LambdaQueryWrapper<AccountPO>()
                        .eq(AccountPO::getUsername, username)
        );
        if (accountPO == null) {
            return Result.fail("未知的账户");
        }
        if (accountPO.getPassword().equals(pwd)) {
            String token = JwtUtil.createToken(accountPO);
            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("username", username);
            res.put("accountId", String.valueOf(accountPO.getId()));
            return Result.success(res);
        } else {
            return Result.fail("账户或密码错误");
        }
    }

    @Override
    public Result doRegister(String username, String password) {
        AccountPO accountPO = baseMapper.selectOne(
                new LambdaQueryWrapper<AccountPO>()
                        .eq(AccountPO::getUsername, username)
        );
        if (accountPO == null) {
            AccountPO newAccount = AccountPO.builder().username(username).password(DigestUtil.md5Hex(password)).build();
            baseMapper.insert(newAccount);
            return Result.success("创建用户成功");
        } else {
            return Result.fail("用户名已存在");
        }
    }
}
