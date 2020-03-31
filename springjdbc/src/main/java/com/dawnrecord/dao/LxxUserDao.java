package com.dawnrecord.dao;

import com.dawnrecord.domain.LxxUser;

import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/22 11:22
 */
public interface LxxUserDao {
    /**
     *
     * @return
     */
    List<LxxUser> findAll();
}
