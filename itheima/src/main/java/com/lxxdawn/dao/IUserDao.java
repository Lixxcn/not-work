package com.lxxdawn.dao;

import com.lxxdawn.domain.QueryVo;
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

    /**
     *
     * @param user
     */
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User findById(int id);

    List<User> findByName(String name);

    int findTotal();

    List<User> findUserByVo(QueryVo vo);


}
