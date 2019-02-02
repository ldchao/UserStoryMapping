package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.UserService;
import cn.edu.nju.user_story_mapping.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by ldchao on 2017/10/15.
 */
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //登录
    @PostMapping(value = "/user/login")
    public UserVO login(String username, String password, HttpServletRequest request) {

        UserVO user = userService.login(username, password);

        if (user.getLoginMessage().equals("success")) {
            if (request.getSession(false) != null)
                request.getSession(false).invalidate();
            request.getSession(true);
            request.getSession().setAttribute("User", user);
        }
        System.out.println("             login happens!"+new Date());
        return user;
    }

    //注销
    @GetMapping(value = "/user/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("User");
        request.getSession(false).invalidate();
        return "logout_success";
    }

    //修改当前用户密码
    @PutMapping(value = "/user/changePassword")
    public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        UserVO userVO = (UserVO) request.getSession(false).getAttribute("User");
        return userService.changePassword(userVO.getId(), oldPassword, newPassword);
    }

    //注册
    @PostMapping(value = "/user/register")
    public String register(String username, String password) {
        return userService.register(username, password);
    }

}
