package com.yiran.nuomi.common;

public interface ErrorCode {

    // 通用异常
    int UNDEFINED_ERROR = 0;
    int PARAMETER_ERROR = 1;

    // 用户异常
    int USER_NOT_LOGIN = 101;
    int USER_LOGIN_FAILURE = 102;

    // 业务异常
    int NOT_Found = 404;

}
