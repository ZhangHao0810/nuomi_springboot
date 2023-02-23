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

    public BusinessException(int code) {
        super();
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

}
