package com.hnguigu.zshop.common.util;

import com.hnguigu.zshop.common.constant.ResponseStatusConstant;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-09 15:48
 **/
public class ResponseResult {

    /**
     * 状态码
     */
    private int status;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", data);
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", null);
    }

    public static ResponseResult success(String message) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, message, null);
    }

    public static ResponseResult fail(Object data) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL, "fail", data);
    }

    public static ResponseResult fail(String message) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL, message, null);
    }

    public static ResponseResult fail() {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_NO_PERMISSION, "fali", null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object date) {
        this.data = date;
    }

}
