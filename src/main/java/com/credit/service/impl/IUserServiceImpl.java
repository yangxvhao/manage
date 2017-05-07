package com.credit.service.impl;

import com.credit.dao.UserMapper;
import com.credit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.credit.service.IUserService;

/**
 * @date：06
 * @author:fushuai
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.insert(user);
    }

    @Override
    public User getUserByName(String name) {
        return this.userMapper.selectByName(name);
    }
}
