package com.lxxdawn.dao;

import com.lxxdawn.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/3/27 14:22
 */
public interface IUserDao1 {
    /**
     * 查询所有操作
     */
    @Select("select * from user")
    List<User> findAll();
}
