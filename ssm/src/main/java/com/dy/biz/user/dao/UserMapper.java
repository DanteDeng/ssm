package com.dy.biz.user.dao;

import java.util.List;
import java.util.Map;

import com.dy.biz.user.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> queryUserList(Map<String, Object> params);

	User selectByLoginNameAndPwd(User user);
}