package run.aiwan.dao;

import run.aiwan.entity.User;

public interface UserDao {
    // 查询用户
    User queryUser(User user);

    // 添加用户
    Integer addUser(User user);
}
