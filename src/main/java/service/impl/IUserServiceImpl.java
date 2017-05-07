package service.impl;

import dao.UserMapper;
import model.User;
import org.springframework.stereotype.Service;
import service.IUserService;

import javax.annotation.Resource;

/**
 * @date：06
 * @author:fushuai
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.insert(user);
    }
}
