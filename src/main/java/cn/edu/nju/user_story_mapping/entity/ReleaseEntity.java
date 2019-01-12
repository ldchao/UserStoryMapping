package cn.edu.nju.user_story_mapping.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2019/1/12.
 */
@Entity
@Table(name = "release", schema = "user_story_mapping")
public class ReleaseEntity {
    private int rid;
    private Integer mid;
    private Timestamp date;

    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseEntity that = (ReleaseEntity) o;

        if (rid != that.rid) return false;
        if (mid != null ? !mid.equals(that.mid) : that.mid != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (mid != null ? mid.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
