package com.yiran.nuomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiran.nuomi.entity.Order;
import com.yiran.nuomi.entity.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from t_order where uid = #{uid}")
    List<Order> selectByUid(int uid);

//  查询所有的订单，同时查询订单的用户
    @Select("select * from t_order")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "user",javaType = User.class,
                one=@One(select = "com.yiran.nuomi.dao.UserMapper.selectById")
            )
    })
    List<Order> selectAllOrdersAndUser();
}