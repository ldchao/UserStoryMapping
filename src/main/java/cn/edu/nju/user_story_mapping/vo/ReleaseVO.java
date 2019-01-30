package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReleaseVO {

    private String rid;
    private String mid;
    private Timestamp date;
    private int code;

    public ReleaseVO(){

    }

    public ReleaseVO(ReleaseEntity releaseEntity) {
        this.mid = releaseEntity.getMid() + "";
        this.date = releaseEntity.getDate();
    }

}
