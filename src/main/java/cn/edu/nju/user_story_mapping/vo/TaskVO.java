package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import lombok.Data;

@Data
public class TaskVO {

    private int aid;
    private int tid;
    private String title;
    private String desc;
    private int code;

    public TaskVO() {

    }

    public TaskVO(TaskEntity taskEntity) {
        this.aid = taskEntity.getAid();
        this.tid = taskEntity.getId();
        this.title = taskEntity.getTitle();
        this.desc = taskEntity.getDescription();
    }
}
