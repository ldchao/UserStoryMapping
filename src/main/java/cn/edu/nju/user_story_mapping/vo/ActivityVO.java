package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import lombok.Data;

@Data
public class ActivityVO {

    private int aid;
    private int mid;
    private String title;
    private String desc;
    private int code;

    public ActivityVO() {
        this.code = 0;
    }

    public ActivityVO(ActivityEntity activityEntity) {
        this.aid = activityEntity.getId();
        this.mid = activityEntity.getMid();
        this.title = activityEntity.getTitle();
        this.desc = activityEntity.getDescription();
        this.code = 1;
    }

}
