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
import java.util.List;

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

        List<MapEntity> maps = mapDao.findByTitleAndDescription(mapTitle, mapDesc);
        MapVO mapVO = new MapVO();
        if (maps == null || maps.size() == 0) {
            mapVO.setMessage("fail");
            return mapVO;
        }

        MapEntity map = maps.get(0);
        int mapId = map.getId();
        UserMapEntity userMapEntity = new UserMapEntity();
        userMapEntity.setMid(mapId);
        userMapEntity.setUid(Integer.parseInt(userId));
        userMapDao.save(userMapEntity);

        mapVO.setId(map.getId() + "");
        mapVO.setMapTitle(mapTitle);
        mapVO.setMapDesc(mapDesc);
        mapVO.setMessage("success");
        return mapVO;
    }

    @Override
    public ArrayList<MapVO> getMapList(String userId) {
        List<UserMapEntity> userMapEntities = userMapDao.findByUid(userId);
        ArrayList<MapVO> mapVOs = new ArrayList<>();
        for (UserMapEntity userMapEntity : userMapEntities) {
            int mapId = userMapEntity.getMid();
            MapEntity map = mapDao.findOne(mapId);
            if (map == null) {
                continue;
            }
            MapVO mapVO = new MapVO();
            mapVO.setId(mapId + "");
            mapVO.setMapTitle(map.getTitle());
            mapVO.setMapDesc(map.getDescription());
            mapVO.setMessage("success");
            mapVOs.add(mapVO);
        }
        return mapVOs;
    }

    @Override
    public String deleteMap(String mapId) {
        MapEntity map = mapDao.findOne(mapId);
        if (map == null) {
            return "fail";
        }
        mapDao.delete(map);
        List<UserMapEntity> userMapEntities = userMapDao.findByMid(mapId);
        if (userMapEntities != null && userMapEntities.size() != 0) {
            for (UserMapEntity userMapEntity : userMapEntities) {
                userMapDao.delete(userMapEntity);
            }
        }
        return "success";
    }
}
