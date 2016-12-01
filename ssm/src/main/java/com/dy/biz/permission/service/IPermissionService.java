package com.dy.biz.permission.service;

import java.util.List;
import java.util.Map;

import com.dy.biz.permission.model.Permission;
import com.dy.biz.user.model.User;
import com.github.pagehelper.PageInfo;

public interface IPermissionService {

	Permission getPermissionById(Permission permission) throws Exception;

	List<Permission> queryPermissionList(Map<String, Object> params) throws Exception;

	void addPermission(Permission permission) throws Exception;

	int updatePermission(Permission permission) throws Exception;
	
	int deletePermission(Permission permission) throws Exception;

	PageInfo<Permission> pagePermissionList(Map<String, Object> params) throws Exception;

	List<Permission> getUserPermissionList(User user) throws Exception;

}
