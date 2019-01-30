package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import lombok.Data;

@Data
public class ActivityVO {

    private String aid;
    private String mid;
    private String title;
    private String desc;
    private int code;

    public ActivityVO(){

    }

    public ActivityVO(ActivityEntity activityEntity) {
        this.mid = activityEntity.getMid() + "";
        this.title = activityEntity.getTitle();
        this.desc = activityEntity.getDescription();
    }

}
