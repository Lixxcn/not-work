package com.lxxdawn.test;

import com.lxxdawn.dao.IUserDao;
import com.lxxdawn.domain.QueryVo;
import com.lxxdawn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/3/31 10:22
 */
public class CrudTest {
    private static Logger logger = Logger.getLogger(CrudTest.class);
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User user : users){
            logger.info(user);
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("modify User property 1");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        userDao.saveUser(user);

        System.out.println("保存操作之后："+user);
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setId(51);
        user.setUsername("modify User property 2");
        user.setAddress("北京市顺义区");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void deleteTest(){
        int id = 51;
        userDao.deleteUser(id);
    }

    @Test
    public void findByIdTest(){
        logger.info(userDao.findById(41));
    }
    @Test
    public void findByNameTest(){
        String name = "%王%";
        List<User> users = userDao.findByName(name);
        for(User user : users){
            logger.info(user);
        }
    }
    @Test
    public void findTotalTest(){
        int count = userDao.findTotal();
        logger.info(count);
    }
    @Test
    public void findUserByVoTest(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> users = userDao.findUserByVo(queryVo);
        for(User u : users){
            logger.info(u);
        }
    }
}
