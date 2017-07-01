package com.credit.service.impl;

import com.credit.dao.UserMapper;
import com.credit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.credit.service.IUserService;

import java.util.List;

/**
 * @date：06
 * @author:yangxvhao
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

    @Override
    public int updateByPrimaryKey(User record) {
        return this.userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return this.userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return this.userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return this.insertSelective(record);
    }

    @Override
    public List<Object> selectAll() {
        return this.userMapper.selectAll();
    }


}
