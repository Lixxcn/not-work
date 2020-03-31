package com.dawnrecord.jdbctemplate;

import com.dawnrecord.dao.LxxUserDao;
import com.dawnrecord.dao.impl.LxxUserDaoImpl;
import com.dawnrecord.domain.LxxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

/**
 * JdbcTemplate的最基本用法
 */
@ContextConfiguration(classes = LxxUserDaoImpl.class)
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        LxxUserDao lxxUserDao = ac.getBean("lxxUserDao",LxxUserDao.class);
        List<LxxUser> lxxUsers = lxxUserDao.findAll();
        System.out.println(lxxUsers);
    }
}
