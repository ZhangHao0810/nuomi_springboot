package com.yiran.nuomi.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Super-Zhang
 * @date 2021-09-16 21:07
 */
@Data //Data会自动生成Getter和Setter函数，故无需写Getter和Setter
public class UserVO {
    private String userName;
    private Integer age;
    private Integer sex;
    private Boolean isVip;
    private Date createTime;
    private List<String> tags;
}
