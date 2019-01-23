package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.UserDao;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
import cn.edu.nju.user_story_mapping.service.UserService;
import cn.edu.nju.user_story_mapping.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ldchao on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserVO login(String username, String password) {
        UserEntity user = userDao.findFirstByUsername(username);
        UserVO userVO = new UserVO();
        if (user == null) {
            userVO.setLoginMessage("no_user");
        } else {
            if (password.equals(user.getPassword())) {
                // 获取上次登录信息
                userVO.setUsername(username);
                userVO.setLoginMessage("success");
            } else {
                userVO.setLoginMessage("wrong_password");
            }
        }
        return userVO;
    }

    @Override
    public String changePassword(String id, String oldPassword, String newPassword) {
        UserEntity user = userDao.findOne(id);
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userDao.saveAndFlush(user);
            return "success";
        } else {
            return "wrong_password";
        }
    }

    @Override
    public String register(String username, String password) {
        if (userDao.findFirstByUsername(username) != null) {
            return "username_existed";
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        userDao.saveAndFlush(user);
        return "success";
    }

}
