package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/10/15.
 */
@Data
public class UserVO implements Serializable {

    private int id;
    private String username;
    private int code;
    private String loginMessage;

    public UserVO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.code = 1;
    }

    public UserVO() {
        this.code = 0;
    }

}
