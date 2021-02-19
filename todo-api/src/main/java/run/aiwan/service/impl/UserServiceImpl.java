package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import run.aiwan.dao.UserDao;
import run.aiwan.entity.User;
import run.aiwan.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryOrAddUserService(User user) {
        return this.userDao.queryUser(user);
    }

    @Override
    public Integer addUserService(User user) {
        return this.userDao.addUser(user);
    }
}
