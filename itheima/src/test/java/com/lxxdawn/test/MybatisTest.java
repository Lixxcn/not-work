package com.lxxdawn.test;

import com.lxxdawn.dao.IUserDao;
import com.lxxdawn.dao.IUserDao1;
//import com.lxxdawn.dao.impl.UserDaoImpl;
import com.lxxdawn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/3/27 11:42
 */
public class MybatisTest {
    private static Logger log = Logger.getLogger(MybatisTest.class);
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws IOException {
        int i = 3;
        log.info("开始执行");
        switch(i){
            case 1:
                //第一种方法，使用XML
                test1();
                break;
            case 2:
                //第二种方法，使用注解
                test2();
                break;
            case 3:
                //第三种方法，使用实体类
//                test3();
                break;
            default:
                log.info("执行完毕");
        }
        log.info("执行完毕");
    }
    static void test1() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
    static void test2() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        IUserDao1 userDao = session.getMapper(IUserDao1.class);//第二种方法，使用注解
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        session.close();
        in.close();
    }
//    static void test3() throws IOException {
//        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        IUserDao iUserDao = new UserDaoImpl(factory);
//        List<User> users = iUserDao.findAll();
//        for(User user : users){
//            System.out.println(user);
//        }
//        in.close();
//    }
}
