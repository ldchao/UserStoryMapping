package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReleaseVO {

    private int rid;
    private int mid;
    private Timestamp date;
    private int code;

    public ReleaseVO() {
        this.code = 0;
    }

    public ReleaseVO(ReleaseEntity releaseEntity) {
        this.rid = releaseEntity.getId();
        this.mid = releaseEntity.getMid();
        this.date = releaseEntity.getDate();
        this.code = 1;
    }

}
