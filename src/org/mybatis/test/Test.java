package org.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.Users;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis简单入门
 *
 * 一般的查询方式
 *
 * 在进行更新、删除、修改的操作时，一定要进行SqlSession的提交，否则是不会到数据库的
 *
 * 除了是使用配置文件的形式书写sql语句外，还可以使用注解的方式进行数据的操作，一般简单的SQL语句可以使用
 * 注解的方式，但是较为复杂的，它就显得力不从心了。
 *
 *
 */
public class Test {
    public static void main(String[] args) throws IOException {

        // 1、获取SqlSessionFactoryBuilder
        InputStream resource = Resources.getResourceAsStream("config.xml");
        // 2、获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        // 3、获取SqlSession
        SqlSession sqlSession = factory.openSession();
//        // 4、执行查询，使用这种方法容易发生字符串拼写错误，因此最好使用面向接口的方式
//        Users users = sqlSession.selectOne("org.mybatis.mapping.UserMapper.getUser", 1);
//        System.out.println(users);

        Test test = new Test();
//        test.testAdd(sqlSession);
//        test.testUpdate(sqlSession);
//        test.testDelete(sqlSession);
        test.testGetAllUser(sqlSession);
    }

    public void testAdd(SqlSession sqlSession) {
        Users users = new Users();
        users.setName(" 新增用户");
        users.setAge(23);
        int result = sqlSession.insert("org.mybatis.mapping.UserMapper.addUser", users);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(result);
    }


    public void testUpdate(SqlSession sqlSession) {
        Users users = new Users();
        users.setId(1);
        users.setName("更新用户");
        users.setAge(21);
        int result = sqlSession.update("org.mybatis.mapping.UserMapper.updateUser", users);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(result);
    }

    public void testDelete(SqlSession sqlSession) {
        int result = sqlSession.delete("org.mybatis.mapping.UserMapper.deleteUser", 2);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(result);
    }

    public void testGetAllUser(SqlSession sqlSession) {
        List<Users> users = sqlSession.selectList("org.mybatis.mapping.UserMapper.getAllUser");
        for (Users user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}
