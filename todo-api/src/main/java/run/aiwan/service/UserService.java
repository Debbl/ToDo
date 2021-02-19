package run.aiwan.service;

import run.aiwan.entity.User;

public interface UserService {
    // 查询用户
    User queryOrAddUserService(User user);

    // 添加用户
    Integer addUserService(User user);
}
