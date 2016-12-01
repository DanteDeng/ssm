package com.dy.biz.role.dao;

import com.dy.biz.role.model.Role;
import com.dy.biz.role.model.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

	void insertAllByRole(Role role);

	int deleteRepeatByRole(Role role);

	int deleteByRole(Role role);
}