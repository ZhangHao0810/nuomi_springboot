package com.yiran.nuomi.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Toolbox implements ErrorCode {

    private static final String salt = "糯米社区，你的专属私人空间";

    public static String md5(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new BusinessException(PARAMETER_ERROR, "参数不合法！");
        }
        return DigestUtils.md5DigestAsHex((str + salt).getBytes());
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
