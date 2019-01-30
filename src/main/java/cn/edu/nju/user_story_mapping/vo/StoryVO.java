package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.StoryEntity;
import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class StoryVO {

    private String sid;
    private String tid;
    private String rid;
    private String title;
    private String desc;
    private int points;
    private String state;
    private Timestamp createAt;
    private int code;

    public StoryVO() {

    }

    public StoryVO(StoryEntity storyEntity) {
        this.tid = storyEntity.getTid()+"";
        this.rid = storyEntity.getRid()+"";
        this.title = storyEntity.getTitle();
        this.desc = storyEntity.getDescription();
        this.points = storyEntity.getStoryPoints();
        this.state = storyEntity.getState();
        this.createAt = storyEntity.getCreateAt();
    }
}
