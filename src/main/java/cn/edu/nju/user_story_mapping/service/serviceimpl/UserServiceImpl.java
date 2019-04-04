package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.UserDao;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
import cn.edu.nju.user_story_mapping.service.UserService;
import cn.edu.nju.user_story_mapping.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                userVO.setId(user.getId());
                userVO.setUsername(username);
                userVO.setCode(1);
                userVO.setLoginMessage("success");
            } else {
                userVO.setCode(0);
                userVO.setLoginMessage("wrong_password");
            }
        }
        return userVO;
    }

    @Override
    public String changePassword(int id, String oldPassword, String newPassword) {
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

    public List<UserVO> searchUser(String name) {
        UserEntity user = userDao.findFirstByUsername(name);
        List<UserVO> userVOS = new ArrayList<>();
        if (user != null) {
            userVOS.add(new UserVO(user));
        }
        List<UserEntity> users = userDao.findByUsernameLike("%" + name + "%");
        if (users != null) {
            for (UserEntity u : users) {
                userVOS.add(new UserVO(u));
            }
        }
        return userVOS;
    }

    @Override
    public List<String> getUsernameList() {
        return userDao.findAllUsername();
    }

    @Override
    public UserVO getUser(int uid) {
        UserEntity user = userDao.findOne(uid);
        if (user == null) {
            return new UserVO();
        }
        return new UserVO(user);
    }
}
