package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.MapDao;
import cn.edu.nju.user_story_mapping.dao.UserMapDao;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.UserMapEntity;
import cn.edu.nju.user_story_mapping.service.MapService;
import cn.edu.nju.user_story_mapping.vo.MapVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapDao mapDao;

    @Autowired
    private UserMapDao userMapDao;

    @Override
    public MapVO addMap(String userId, String mapTitle, String mapDesc) {
        MapEntity mapEntity = new MapEntity();
        mapEntity.setDescription(mapDesc);
        mapEntity.setTitle(mapTitle);
        mapDao.save(mapEntity);
        MapEntity map = mapDao.findByTitle(mapTitle);
        int mapId = map.getId();
        UserMapEntity userMapEntity = new UserMapEntity();
        userMapEntity.setMid(mapId);
        userMapEntity.setUid(Integer.parseInt(userId));
        userMapDao.save(userMapEntity);
        MapVO mapVO = new MapVO();
        if (map == null) {
            mapVO.setMessage("fail");
        } else {
            mapVO.setId(map.getId() + "");
            mapVO.setMapTitle(mapTitle);
            mapVO.setMapDesc(mapDesc);
            mapVO.setMessage("success");
        }
        return mapVO;
    }

    @Override
    public ArrayList<MapVO> getMapList(String userId) {
        return null;
    }

    @Override
    public String deleteMap(String mapId) {
        return null;
    }
}
