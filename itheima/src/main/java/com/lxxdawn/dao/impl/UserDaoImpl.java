//package com.lxxdawn.dao.impl;
//
//import com.lxxdawn.dao.IUserDao;
//import com.lxxdawn.domain.User;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import java.util.List;
//
///**
// * @author Li-Xiaoxu
// * @version 1.0
// * @date 2020/3/27 14:42
// */
//public class UserDaoImpl implements IUserDao {
//    private SqlSessionFactory factory;
//    public UserDaoImpl(SqlSessionFactory factory){
//        this.factory = factory;
//    }
//
//    public List<User> findAll() {
//        SqlSession sqlSession = factory.openSession();
//        List<User> users = sqlSession.selectList("com.lxxdawn.dao.IUserDao.findAll");
//        return users;
//    }
//
//    @Override
//    public void saveUser(User user) {
//
//    }
//}
