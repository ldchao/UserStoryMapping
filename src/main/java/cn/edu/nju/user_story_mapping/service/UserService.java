package cn.edu.nju.user_story_mapping.service;


import cn.edu.nju.user_story_mapping.vo.UserVO;


/**
 * Created by ldchao on 2017/10/15.
 */
public interface UserService {

    UserVO login(String username, String password);

    String changePassword(String id, String oldPassword, String newPassword);

    String register(String username, String password);

}
