package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.InviteEntity;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface InviteDao extends JpaRepository<InviteEntity,Serializable> {

    InviteEntity findFirstByInviterIdAndInviteeIdAndMid(int inviterId,int inviteeId,int mid);

    List<InviteEntity> findByInviteeId(int inviteeId);

    List<InviteEntity> findByInviterId(int inviterId);

}
