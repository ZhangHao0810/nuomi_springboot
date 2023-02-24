package com.yiran.nuomi.common;

public interface StateCode {

    enum Code {

        // 成功
        SUCCESS(200),

        //失败
        ERROR(-200),

        //未认证
        UNAUTHORIZED(401),

        //接口不存在
        NOT_FOUND(404),

        //请求方式不支持
        METHOD_NOT_ALLOWED(405),

        //服务异常
        INTERNAL_SERVER_ERROR(500);

        //状态响应码
        private int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode(){
            return code;
        }


    }

}
