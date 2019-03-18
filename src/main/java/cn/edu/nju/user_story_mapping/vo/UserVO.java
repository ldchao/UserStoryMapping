package cn.edu.nju.user_story_mapping.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/10/15.
 */
@Data
public class UserVO implements Serializable{

    private int id;
    private String username;
    private int code;
    private String loginMessage;

}
