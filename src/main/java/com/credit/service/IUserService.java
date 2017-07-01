package com.credit.service;

import com.credit.model.User;

import java.util.List;

/**
 * @date：06
 * @author:yangxvhao
 */
public interface IUserService {

    public User getUserById(int userId);

    public int addUser(User user);

    public User getUserByName(String name);

    public int updateByPrimaryKey(User record);

    public int updateByPrimaryKeySelective(User record);

    public int deleteByPrimaryKey(Integer id);

    public int insert(User record);

    public int insertSelective(User record);

    List<Object> selectAll();
}
