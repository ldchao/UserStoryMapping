package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.InviteEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/10/15.
 */
@Data
public class InviteVO implements Serializable{

    private int id;
    private Integer inviterId;
    private Integer inviteeId;
    private Integer mid;
    private String state;
    private int code;

    public InviteVO(){
        this.code = 0;
    }

    public InviteVO(InviteEntity invite){
        this.id = invite.getId();
        this.inviterId = invite.getInviterId();
        this.inviteeId = invite.getInviteeId();
        this.mid = invite.getMid();
        this.state = invite.getState();
        this.code = 1;
    }
}
