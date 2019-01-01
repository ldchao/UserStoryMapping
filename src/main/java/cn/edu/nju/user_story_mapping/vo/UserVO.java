package cn.edu.nju.user_story_mapping.vo;

/**
 * Created by ldchao on 2017/10/15.
 */
public class UserVO {

    private String username;
    private String license;
    private String loginMessage;

    public UserVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
