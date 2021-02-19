package run.aiwan.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.aiwan.entity.User;
import run.aiwan.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/todo")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user")
    public Map<Object,Object> doQueryUser(User user) {
        HashMap<Object, Object> jsons = new HashMap<>();
        // 是否已经存在该用户
        User TargetUser = this.userService.queryOrAddUserService(user);
        // 如果该用户没有注册
        if (TargetUser == null) {
            Integer addResult = this.userService.addUserService(user);
            if (addResult == 1) {
                jsons.put("isRegistered", false);
                jsons.put("idAddUser", true);
            } else {
                jsons.put("isAddUser", false);
            }
        } else { // 存在该用户
            jsons.put("uid", TargetUser.getUid());
            jsons.put("uname", TargetUser.getUname());
            jsons.put("isAddUser", false);
            jsons.put("isRegistered", true);
        }
        return jsons;
    }
}
