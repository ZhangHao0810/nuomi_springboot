package com.yiran.nuomi.common;

import lombok.Data;

/**
 * @author SuperZhang
 * @version 1.Bootstrap
 * @description: RuntimeException 意味着无需抛出异常，可以统一所有的异常到Controller来抛出。
 * @date 2021-09-10 20:32
 */
@Data
public class BusinessException extends RuntimeException {

    private int code;

    private String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(StateCode.Code code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
