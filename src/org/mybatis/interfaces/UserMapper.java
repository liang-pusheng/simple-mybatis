package org.mybatis.interfaces;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.domain.Users;

import java.util.List;

/**
 * 定义sql映射的接口，使用注解指明方法要执行的sql
 */
public interface UserMapper {

    // 根据id查询用户
    @Select("select * from users where id = #{id}")
    Users selectUser(int id);

    // 添加用户
    @Insert("insert into users (name, age) values (#{name}, #{age})")
    int addUser(Users users);

    // 更新用户
    @Update("update users set name = #{name}, age = #{age} where id = #{id}")
    int updateUser(int id);

    // 删除用户
    @Delete("delete from users where id = #{id}")
    int deleteUser(int id);

    // 查询所有用户
    @Select("select * from users")
    List<Users> selectAllUser();
}
