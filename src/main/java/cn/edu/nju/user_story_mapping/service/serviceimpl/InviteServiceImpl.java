package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.InviteDao;
import cn.edu.nju.user_story_mapping.dao.UserDao;
import cn.edu.nju.user_story_mapping.entity.InviteEntity;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
import cn.edu.nju.user_story_mapping.service.InviteService;
import cn.edu.nju.user_story_mapping.vo.InviteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteDao inviteDao;

    @Autowired
    private UserDao userDao;

    @Override
    public InviteVO invite(int inviterId, int inviteeId, int mapId) {
        if (inviteDao.findFirstByInviterIdAndInviteeIdAndMid(inviterId, inviteeId, mapId) != null) {
            return new InviteVO();
        }
        InviteEntity invite = new InviteEntity();
        invite.setInviterId(inviterId);
        invite.setInviteeId(inviteeId);
        invite.setMid(mapId);
        invite.setState("unanswered");
        inviteDao.save(invite);
        return new InviteVO(invite);
    }

    @Override
    public List<InviteVO> checkInvite(int uid) {
        ArrayList<InviteVO> inviteVOS = new ArrayList<>();
        UserEntity user = userDao.findOne(uid);
        if (user == null) {
            return inviteVOS;
        }
        return this.EntitiesToVOs(inviteDao.findByInviterId(uid));
    }

    @Override
    public List<InviteVO> checkInvited(int uid) {
        ArrayList<InviteVO> inviteVOS = new ArrayList<>();
        UserEntity user = userDao.findOne(uid);
        if (user == null) {
            return inviteVOS;
        }
        return this.EntitiesToVOs(inviteDao.findByInviteeId(uid));
    }

    @Override
    public InviteVO answerInvite(int inviteId, String answer) {
        InviteEntity invite = inviteDao.findOne(inviteId);
        if (invite == null || (!invite.getState().equals("unanswered"))) {
            return new InviteVO();
        }
        invite.setState(answer);
        inviteDao.save(invite);
        return new InviteVO(invite);
    }

    private List<InviteVO> EntitiesToVOs(List<InviteEntity> invites) {
        List<InviteVO> inviteVOs = new ArrayList<>();
        if (invites == null) {
            return inviteVOs;
        }
        for (InviteEntity invite : invites) {
            inviteVOs.add(new InviteVO(invite));
        }
        return inviteVOs;
    }
}
