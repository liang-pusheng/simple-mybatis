package im.lazycat.interfaces;

import im.lazycat.entity.SysRole;
import im.lazycat.entity.SysUser;

import java.util.List;

public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long id);

    int insertUser(SysUser user);

    int updateUserById(SysUser user);

    int deleteUserById(Long id);

    // 动态SQL
    List<SysUser> selectUser(SysUser user);

    int updateByIdSelective(SysUser user);

    SysUser selectUserByIdOrUsername(SysUser user);

    List<SysUser> selectByIdList(List<Long> idList);
}
