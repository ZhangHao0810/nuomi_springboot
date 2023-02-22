package com.yiran.nuomi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@TableName("user_info")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "昵称不能为空且不能与库中已有昵称重复")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "年龄不能为空")
    @Min(value = 1, message = "年龄不能小于1")
    @Max(value = 200, message = "年龄不能超过200")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private Integer role;

    //  描述用户的所有订单 selsec id,username,password, orders form t_user.....
    @TableField(exist = false)
    private List<Order> orders;


//    alt + insert
}
