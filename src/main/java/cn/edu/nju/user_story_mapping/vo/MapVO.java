package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.MapEntity;
import lombok.Data;


@Data
public class MapVO {

    private int id;
    private String mapTitle;
    private String mapDesc;
    private int code;

    public MapVO(){
        this.code = 0;
    }

    public MapVO(MapEntity mapEntity) {
        this.id = mapEntity.getId();
        this.mapTitle = mapEntity.getTitle();
        this.mapDesc = mapEntity.getDescription();
        this.code = 1;
    }
}
