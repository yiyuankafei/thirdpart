package com.leadpms.thirdpart.common.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private int code;
    private T data;
    private String msg;

    private final static int SUCCESS_CODE = 200;
    private final static int ERROR_CODE = 400;

    public static <T> BaseResponse success(T data) {
        BaseResponse response = new BaseResponse();
        response.setData(data);
        response.setCode(BaseResponse.SUCCESS_CODE);
        return response;
    }

    public static BaseResponse success() {
        BaseResponse response = new BaseResponse();
        response.setCode(BaseResponse.SUCCESS_CODE);
        return response;
    }

    public static BaseResponse error(String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(BaseResponse.ERROR_CODE);
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse success(String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(BaseResponse.SUCCESS_CODE);
        response.setMsg(msg);
        return response;
    }
}
