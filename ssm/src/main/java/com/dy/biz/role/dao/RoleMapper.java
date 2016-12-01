package com.dy.biz.role.dao;

import java.util.List;
import java.util.Map;

import com.dy.biz.role.model.Role;
import com.dy.biz.user.model.User;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> queryRoleList(Map<String, Object> params);

	List<Role> queryUserRoleList(User result);

}