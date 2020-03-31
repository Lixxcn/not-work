package com.dawnrecord.dao.impl;

import com.dawnrecord.dao.LxxUserDao;
import com.dawnrecord.domain.LxxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/22 11:23
 */
public class LxxUserDaoImpl implements LxxUserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LxxUser> findAll() {
        return jdbcTemplate.query("select * from lxx_user", new BeanPropertyRowMapper<LxxUser>(LxxUser.class));
    }
}
