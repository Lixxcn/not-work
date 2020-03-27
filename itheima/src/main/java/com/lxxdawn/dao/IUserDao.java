package com.lxxdawn.dao;

import com.lxxdawn.domain.User;

import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/3/27 11:25
 *
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     */
    List<User> findAll();
}
