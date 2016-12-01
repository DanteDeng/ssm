package com.dy.biz.user.dao;

import com.dy.biz.user.model.User;
import com.dy.biz.user.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String urId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String urId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

	void insertAllByUser(User user);

	int deleteRepeatByUser(User user);

	int deleteByUser(User user);
}