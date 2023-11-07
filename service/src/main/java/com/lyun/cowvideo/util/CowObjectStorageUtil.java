package com.lyun.cowvideo.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lyun.cowvideo.entity.CowUploadDo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CowObjectStorageUtil {

    private final static String HOST = "s30idqjhw.bkt.clouddn.com";

    private final static String ACCESS_KEY = "uiHNhZ6XvH7AkaTFTO9GYsGaO0Z1jB01mCUKBlQz";

    private final static String SECRET_KEY = "xcGrDgPni1MrdxKpdBcJMxlG2lnJ8qiuEsiu58jZ";

    private final static String BUCKET = "verybigniuniu";

    /**
     * 获取上传的token
     */
    public static String getUploadToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(BUCKET);
    }

    public static String getHost() {
        return HOST;
    }

    public static CowUploadDo upload(InputStream inputStream) {
        Configuration cfg = new Configuration();
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;

        UploadManager uploadManager = new UploadManager(cfg);
        // 如果key为null， 默认生成hash作为文件名(key)
        String key = null;

        String upToken = getUploadToken();

        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            JSONObject bodyJson = JSONUtil.parseObj(response.bodyString());
            return CowUploadDo.builder().hash(bodyJson.getStr("hash")).key(bodyJson.getStr("key")).build();
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }

}
