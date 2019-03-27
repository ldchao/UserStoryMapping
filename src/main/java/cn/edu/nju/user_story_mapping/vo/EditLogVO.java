package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.EditLogEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EditLogVO {
    private int id;
    private Integer uid;
    private Integer mid;
    private String type;
    private Integer itemId;
    private String desc;
    private Timestamp time;
    private int code;

    public EditLogVO() {
        this.code = 0;
    }

    public EditLogVO(EditLogEntity editLog) {
        this.id = editLog.getId();
        this.uid = editLog.getUid();
        this.mid = editLog.getMid();
        this.type = editLog.getType();
        this.itemId = editLog.getItemId();
        this.desc = editLog.getDesc();
        this.time = editLog.getEditAt();
        this.code = 1;
    }
}
