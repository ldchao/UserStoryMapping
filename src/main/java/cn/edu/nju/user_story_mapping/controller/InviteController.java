package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.InviteService;
import cn.edu.nju.user_story_mapping.vo.InviteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ldchao on 2017/10/15.
 */
@RestController
@CrossOrigin
public class InviteController {

    private final InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping(value = "/invite/invite_user")
    public InviteVO invite(int inviterId, int inviteeId, int mapId) {
        return inviteService.invite(inviterId, inviteeId, mapId);
    }

    @GetMapping(value = "/invite/check_invite")
    public List<InviteVO> checkInvite(int uid) {
        return inviteService.checkInvite(uid);
    }

    @GetMapping(value = "/invite/check_invited")
    public List<InviteVO> checkInvited(int uid) {
        return inviteService.checkInvited(uid);
    }

    @PostMapping(value = "/invite/answer_invited")
    public InviteVO answerInvite(int inviteId, String answer) {
        return inviteService.answerInvite(inviteId, answer);
    }
}
