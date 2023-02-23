package com.yiran.nuomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiran.nuomi.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//用于操作用户表,MyBaits会根据Mapper注解，动态实现UserMapper接口（实现类），动态代理技术
//Spring会自动创建UserMapper接口实现类对应的实例
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table user_info
//     *
//     * @mbg.generated Fri Sep 17 19:35:16 CST 2021
//     */
//    int insert(User record);
//
//    /**
//     * 根据昵称查询，库中昵称唯一。
//     *
//     * @param username
//     * @return
//     */
//    User selectByUserName(String username);


    //   查询用户及其所有的订单
    @Select("select * from t_user")
    @Results(
            {
                    @Result(column = "id", property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "birthday", property = "birthday"),
                    @Result(column = "id", property = "orders", javaType = List.class,
                            many = @Many(select = "com.yiran.nuomi.dao.OrderMapper.selectByUid")
                    )
            }
    )
    List<User> selectAllUserAndOrders();

}