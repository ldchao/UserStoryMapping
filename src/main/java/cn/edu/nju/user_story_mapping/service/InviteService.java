package cn.edu.nju.user_story_mapping.service;


import cn.edu.nju.user_story_mapping.vo.InviteVO;

import java.util.List;


/**
 * Created by ldchao on 2017/10/15.
 */
public interface InviteService {

    InviteVO invite(int inviterId, int inviteeId, int mapId);

    List<InviteVO> checkInvite(int uid);

    List<InviteVO> checkInvited(int uid);

    InviteVO answerInvite(int inviteId, String answer);
}
