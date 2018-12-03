package im.lazycat.test;

import im.lazycat.entity.SysRole;
import im.lazycat.entity.SysUser;
import im.lazycat.interfaces.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        InputStream resource = Resources.getResourceAsStream("config.xml");
        // 2、获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        // 3、获取SqlSession
        SqlSession sqlSession = factory.openSession();

//        testSelectById(sqlSession);
//        testSelectAll(sqlSession);
//        testSelectRolesByUserId(sqlSession);
//        testInsertUser(sqlSession);
//        testUpdateUserById(sqlSession);
//        testDeleteUserById(sqlSession);
//        testSelectUser(sqlSession);
        testSelectUserByIdOrUsername(sqlSession);
    }

    private static void testSelectById(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = mapper.selectById(1L);
        if (user != null) {
            System.out.println(user.getUserName());
        }
        sqlSession.close();
    }

    private static void testSelectAll(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SysUser> sysUsers = mapper.selectAll();
        if (sysUsers != null) {
            for (SysUser sysUser : sysUsers) {
                System.out.println(sysUser.getUserName());
            }
        }
        sqlSession.close();
    }

    private static void testSelectRolesByUserId(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SysRole> roles = mapper.selectRolesByUserId(1L);
        if (roles != null) {
            for (SysRole role : roles) {
                System.out.println(role.getRoleName());
            }
        }
        sqlSession.close();
    }

    private static void testInsertUser(SqlSession sqlSession) {
        SysUser user = new SysUser();
        user.setUserName("test1");
        user.setUserPassword("09876");
        user.setUserEmail("test1@test.com");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[]{1, 2, 3});// 随便设一个
        user.setCreateTime(new Date());
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.insertUser(user);
        if (i > 0) {
            System.out.println("添加成功!");
        }
        Long id = user.getId();
        System.out.println("新增用户ID：" + id);
        sqlSession.commit();
        sqlSession.close();
    }

    private static void testUpdateUserById(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = mapper.selectById(1L);
        user.setUserName("one");
        user.setUserPassword("654321");
        int i = mapper.updateUserById(user);
        if (i > 0) {
            System.out.println("更新成功!");
        }
        SysUser sysUser = mapper.selectById(1L);
        System.out.println(sysUser.getUserName() + ":" + sysUser.getUserPassword());
        sqlSession.commit();
        sqlSession.close();
    }

    private static void testDeleteUserById(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUserById(1005L);
        if (i > 0) {
            System.out.println("删除成功!");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    // 测试动态SQL
    private static void testSelectUser(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 只查询用户名时
        SysUser user = new SysUser();
        user.setUserName("te");
        List<SysUser> users = mapper.selectUser(user);
        if (users.size() > 0) {
            for (SysUser sysUser : users) {
                System.out.println("只查询用户名：" + sysUser.getUserName());
            }
        }
        // 只查询Email时
        user = new SysUser();
        user.setUserEmail("test@qq.com");
        List<SysUser> sysUsers = mapper.selectUser(user);
        if (sysUsers.size() > 0) {
            for (SysUser sysUser : sysUsers) {
                System.out.println("只查Email：" + sysUser.getUserName() + ";" + sysUser.getUserEmail());
            }
        }
        // 同时查询用户名和邮箱时
        user = new SysUser();
        user.setUserName("te");
        user.setUserEmail("admin@qq.com");
        List<SysUser> userList = mapper.selectUser(user);
        if (userList.size() > 0) {
            for (SysUser sysUser : userList) {
                System.out.println("同时查询用户和邮箱：" + sysUser.getUserName() + ":" + sysUser.getUserEmail());
            }
        } else {
            System.out.println("同时查询用户和邮箱：null");
        }
        sqlSession.close();
    }

    public static void testSelectUserByIdOrUsername(SqlSession sqlSession) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 当只有ID时
        SysUser user = new SysUser();
        user.setId(1L);
        SysUser sysUser = mapper.selectUserByIdOrUsername(user);
        System.out.println("只有ID时:" + sysUser.getId() + ":" + sysUser.getUserName());
        // 当只有名称时
        user.setId(null);
        user.setUserName("test");
        sysUser = mapper.selectUserByIdOrUsername(user);
        System.out.println("只有名称时：" + sysUser.getId() + ":" + sysUser.getUserName());
        // ID和名称都没有时
        user.setUserName(null);
        sysUser = mapper.selectUserByIdOrUsername(user);
        if (sysUser == null) {
            System.out.println("未查到任何数据！");
        }
    }
}
