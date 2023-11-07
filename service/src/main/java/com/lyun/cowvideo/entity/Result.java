package com.lyun.cowvideo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Result {

    private String code;

    private String msg;

    private String time;

    private Object data;

    public Result(String code, String msg, String time, Object data) {
        this.code = code;
        this.msg = msg;
        this.time = time;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result("000", "处理成功", new Timestamp(System.currentTimeMillis()).toString(), data);
    }

    public static Result success(){
        return success(null);
    }

    public static Result fail(Object data){
        return new Result("-1", "处理失败", new Timestamp(System.currentTimeMillis()).toString(), data);
    }

    public static Result fail(){
        return fail(null);
    }

}
