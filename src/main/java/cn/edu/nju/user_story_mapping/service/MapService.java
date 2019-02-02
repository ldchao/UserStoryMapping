package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.MapVO;

import java.util.ArrayList;

public interface MapService {

    MapVO addMap(int userId, String mapTitle, String mapDesc);

    ArrayList<MapVO> getMapList(int userId);

    String deleteMap(int userId, int mapId);
}
