package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.MapService;
import cn.edu.nju.user_story_mapping.vo.MapVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MapController {
    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping(value = "/map/add_map")
    public MapVO addMap(String userId, String mapTitle, String mapDesc) {
        return mapService.addMap(userId, mapTitle, mapDesc);
    }

    @GetMapping(value="/map/map_list")
    public ArrayList<MapVO> getMapList(String userId){
        return mapService.getMapList(userId);
    }

    @PostMapping(value="/map/delete_map")
    public String deleteMap(String mapId){
        return mapService.deleteMap(mapId);
    }
}
