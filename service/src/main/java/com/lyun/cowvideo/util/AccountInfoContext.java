package com.lyun.cowvideo.util;

import com.lyun.cowvideo.entity.po.AccountPO;

public class AccountInfoContext {

    private static final ThreadLocal<AccountPO> threadLocal = new ThreadLocal<>();

    public static void setInfo(AccountPO accountPO){
        threadLocal.set(accountPO);
    }

    public static AccountPO getInfo(){
        return threadLocal.get();
    }

}
