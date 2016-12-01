package com.dy.biz.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.biz.permission.dao.PermissionMapper;
import com.dy.biz.permission.model.Permission;
import com.dy.biz.permission.service.IPermissionService;
import com.dy.biz.user.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class PermissionService implements IPermissionService{
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Permission getPermissionById(Permission permission) throws Exception {
		return permissionMapper.selectByPrimaryKey(permission.getPermissionId());
	}

	@Override
	public List<Permission> queryPermissionList(Map<String, Object> params) throws Exception {
		return permissionMapper.queryPermissionList(params);
	}

	@Override
	public void addPermission(Permission permission) throws Exception {
		permissionMapper.insert(permission);
	}

	@Override
	public int updatePermission(Permission permission) throws Exception {
		return permissionMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public int deletePermission(Permission permission) throws Exception {
		return permissionMapper.deleteByPrimaryKey(permission.getPermissionId());
	}

	@Override
	public PageInfo<Permission> pagePermissionList(Map<String, Object> params) throws Exception {
		Page<Permission> result = PageHelper.startPage((Integer)params.get("pageNum"), (Integer)params.get("pageSize"));
		permissionMapper.queryPermissionList(params); 
	    return result.toPageInfo();
	}

	@Override
	public List<Permission> getUserPermissionList(User user) throws Exception {
		return permissionMapper.getUserPermissionList(user);
	}

}
