package com.lyun.cowvideo.rest;

import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.util.AccountInfoContext;
import com.lyun.cowvideo.util.CowObjectStorageUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cow")
@CrossOrigin
public class CowController {

    @RequestMapping("/getInfo")
    public Result getUploadInfo(){
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("uploadToken", CowObjectStorageUtil.getUploadToken());
        resMap.put("host", CowObjectStorageUtil.getHost());
        return Result.success(resMap);
    }


}
