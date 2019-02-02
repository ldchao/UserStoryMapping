package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.*;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
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
    private UserDao userDao;

    @Autowired
    private UserMapDao userMapDao;

    @Override
    public MapVO addMap(int userId, String mapTitle, String mapDesc) {
        if (userDao.findOne(userId) == null) {
            return new MapVO();
        }

        MapEntity map = new MapEntity();
        map.setDescription(mapDesc);
        map.setTitle(mapTitle);
        mapDao.save(map);

        UserMapEntity userMap = new UserMapEntity();
        userMap.setMid(map.getId());
        userMap.setUid(userId);
        userMapDao.save(userMap);

        return new MapVO(map);
    }

    @Override
    public ArrayList<MapVO> getMapList(int userId) {
        List<UserMapEntity> userMaps = userMapDao.findByUid(userId);
        ArrayList<MapVO> mapVOs = new ArrayList<>();
        for (UserMapEntity userMap : userMaps) {
            int mapId = userMap.getMid();
            MapEntity map = mapDao.findOne(mapId);
            if (map == null) {
                continue;
            }
            MapVO mapVO = new MapVO(map);
            mapVO.setCode(1);
            mapVOs.add(mapVO);
        }
        return mapVOs;
    }

    @Override
    public String deleteMap(int userId, int mapId) {
        MapEntity map = mapDao.findOne(mapId);
        UserEntity user = userDao.findOne(userId);
        UserMapEntity userMap = userMapDao.findFirstByUidAndMid(userId, mapId);
        if ((map == null) || (user == null) || (userMap == null)) {
            return "fail";
        }
        userMapDao.delete(userMap);
        return "success";
    }
}
