package com.dy.biz.permission.dao;

import java.util.List;
import java.util.Map;

import com.dy.biz.permission.model.Permission;
import com.dy.biz.role.model.Role;
import com.dy.biz.user.model.User;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

	List<Permission> queryPermissionList(Map<String, Object> params);

	List<Permission> getUserPermissionList(User user);

	List<Permission> getRolePermissionList(Role role);
}