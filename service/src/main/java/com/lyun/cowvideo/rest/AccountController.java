package com.lyun.cowvideo.rest;

import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.vo.AccountBaseInfoVo;
import com.lyun.cowvideo.service.AccountService;
import com.lyun.cowvideo.service.CollectService;
import com.lyun.cowvideo.service.LikeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/account")
@Api(tags = "账户接口")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private LikeInfoService likeInfoService;

    @RequestMapping("/auth")
    @ApiOperation("登录接口")
    public Result a9uth(String username, String password){
        return accountService.doAuth(username, password);
    }

    @RequestMapping("/register")
    @ApiOperation("注册接口")
    public Result register(String username, String password){
        return accountService.doAuth(username, password);
    }

    @RequestMapping("/getBaseInfo")
    @ApiOperation("通过用户id获取用户基本信息")
    public Result getAccountBaseInfo(Integer id){
        AccountPO po = accountService.getById(id);
        if (po == null){
            return Result.fail("未知的用户");
        }
        AccountBaseInfoVo vo = AccountBaseInfoVo.builder()
                .id(po.getId()).name(po.getName()).username(po.getUsername()).build();
        return Result.success(vo);
    }

    @RequestMapping("/collectList")
    @ApiOperation("获取收藏列表")
    public Result getCollectList(){
        return collectService.collectList();
    }


    @RequestMapping("/likeList")
    @ApiOperation("获取喜欢列表")
    public Result getLikeList(){
        return likeInfoService.getLikeList();
    }

}
