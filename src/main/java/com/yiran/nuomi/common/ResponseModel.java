package com.yiran.nuomi.common;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;

public class ResponseModel extends HashMap<String, Object> implements StateCode {

    // 根据前端快速配置 业务响应信息
    private static final String STATE_TAG = "state";
    private static final String MSG_TAG = "message";
    private static final String DATA_TAG = "data";

    public ResponseModel() {
    }

    public ResponseModel(Object data) {
        super.put(STATE_TAG, Code.SUCCESS);
        super.put(MSG_TAG, Code.SUCCESS);
        if (!ObjectUtil.isEmpty(data)){
            super.put(DATA_TAG, data);
        }
    }

    public ResponseModel(Code code, Object data) {
        super.put(STATE_TAG, code);
        super.put(MSG_TAG, code);
        if (!ObjectUtil.isEmpty(data)){
            super.put(DATA_TAG, data);
        }
    }

    /**
     * @description 自定义返回内容
     * @author zh
     * @data 2023/2/24
     */
    public ResponseModel(int state, String message) {
        super.put(STATE_TAG, state);
        super.put(MSG_TAG, message);
    }

    public ResponseModel(int state, String message, Object data) {
        super.put(STATE_TAG, state);
        super.put(MSG_TAG, message);
        if (!ObjectUtil.isEmpty(data)){
            super.put(DATA_TAG, data);
        }
    }

}
