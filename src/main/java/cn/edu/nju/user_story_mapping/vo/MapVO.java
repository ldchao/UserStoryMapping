package cn.edu.nju.user_story_mapping.vo;

import cn.edu.nju.user_story_mapping.entity.MapEntity;
import lombok.Data;


@Data
public class MapVO {

    private String id;
    private String mapTitle;
    private String mapDesc;
    private int code;

    public MapVO(){

    }

    public MapVO(MapEntity mapEntity) {
        this.mapTitle = mapEntity.getTitle();
        this.mapDesc = mapEntity.getDescription();
    }
}
