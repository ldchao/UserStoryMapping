package cn.edu.nju.user_story_mapping.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ldchao on 2019/3/21.
 */
@Entity
@Table(name = "invite", schema = "user_story_mapping")
public class InviteEntity {
    private int id;
    private Integer inviterId;
    private Integer inviteeId;
    private Integer mid;
    private String state;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "inviter_id")
    public Integer getInviterId() {
        return inviterId;
    }

    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    @Basic
    @Column(name = "invitee_id")
    public Integer getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Integer inviteeId) {
        this.inviteeId = inviteeId;
    }

    @Basic
    @Column(name = "mid")
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InviteEntity that = (InviteEntity) o;

        if (id != that.id) return false;
        if (inviterId != null ? !inviterId.equals(that.inviterId) : that.inviterId != null) return false;
        if (inviteeId != null ? !inviteeId.equals(that.inviteeId) : that.inviteeId != null) return false;
        if (mid != null ? !mid.equals(that.mid) : that.mid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inviterId != null ? inviterId.hashCode() : 0);
        result = 31 * result + (inviteeId != null ? inviteeId.hashCode() : 0);
        result = 31 * result + (mid != null ? mid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
