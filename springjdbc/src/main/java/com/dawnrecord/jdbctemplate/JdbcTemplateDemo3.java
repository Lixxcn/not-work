package com.dawnrecord.jdbctemplate;

import com.dawnrecord.domain.LxxUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //3.执行操作
        Date date = ac.getBean("date",Date.class);
//        jt.execute("insert into account(name,money)values('ddd',2222)");

//        jt.update("insert into lxx_user(id,username,pwd,sex)values(?,?,?,?)",4,"dawnrecord","dawnrecord","女");

        List<LxxUser> lxxUsers = jt.query("select * from lxx_user where id = ?", new BeanPropertyRowMapper<LxxUser>(LxxUser.class), 1);
        for (LxxUser lxxUser : lxxUsers) {
            System.out.println(lxxUser);
        }
        Long num = jt.queryForObject("select count(*) from lxx_user",Long.class);
        System.out.println(num);

//        System.out.println(lxxUsers);

//        jt.execute("insert into user(username,sex,address)values('test','男','北京')");
//        jt.update("update user set username = ? ,sex = ?, address = ? where id = ?","test2","女", "河北", 52);


     /*   //准备数据源：spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eesy");
        ds.setUsername("root");
        ds.setPassword("1234");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //给jt设置数据源
        jt.setDataSource(ds);
        //2.执行操作
        jt.execute("insert into account(name,money)values('ccc',1000)");*/
    }
}
