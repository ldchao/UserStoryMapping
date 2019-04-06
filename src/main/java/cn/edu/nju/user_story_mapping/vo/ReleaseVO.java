package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReleaseVO {

    private int rid;
    private int mid;
    private Timestamp endAt;
    private Timestamp startAt;
    private int code;

    public ReleaseVO() {
        this.code = 0;
    }

    public ReleaseVO(ReleaseEntity releaseEntity) {
        this.rid = releaseEntity.getId();
        this.mid = releaseEntity.getMid();
        this.startAt = releaseEntity.getStartAt();
        this.endAt = releaseEntity.getEndAt();
        this.code = 1;
    }

}
