package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.dao.MapDao;
import cn.edu.nju.user_story_mapping.dao.UserMapDao;
import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.UserMapEntity;
import cn.edu.nju.user_story_mapping.service.ActivityService;
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

    @Autowired
    private ActivityDao activityDao;

    @Override
    public MapVO addMap(String userId, String mapTitle, String mapDesc) {
        MapEntity mapTemp = new MapEntity();
        mapTemp.setDescription(mapDesc);
        mapTemp.setTitle(mapTitle);
        mapDao.save(mapTemp);

        MapEntity map = mapDao.findFirstByTitleAndDescription(mapTitle, mapDesc);
        MapVO mapVO = new MapVO();
        if (map == null) {
            mapVO.setCode(0);
            return mapVO;
        }

        int mapId = map.getId();
        UserMapEntity userMap = new UserMapEntity();
        userMap.setMid(mapId);
        userMap.setUid(Integer.parseInt(userId));
        userMapDao.save(userMap);

        mapVO = new MapVO(map);
        mapVO.setId(map.getId() + "");
        mapVO.setCode(1);
        return mapVO;
    }

    @Override
    public ArrayList<MapVO> getMapList(String userId) {
        List<UserMapEntity> userMaps = userMapDao.findByUid(userId);
        ArrayList<MapVO> mapVOs = new ArrayList<>();
        for (UserMapEntity userMap : userMaps) {
            int mapId = userMap.getMid();
            MapEntity map = mapDao.findOne(mapId);
            if (map == null) {
                continue;
            }
            MapVO mapVO = new MapVO(map);
            mapVO.setId(mapId + "");
            mapVO.setCode(1);
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

        List<UserMapEntity> userMaps = userMapDao.findByMid(mapId);
        if (userMaps != null && userMaps.size() != 0) {
            for (UserMapEntity userMap : userMaps) {
                userMapDao.delete(userMap);
            }
        }

        List<ActivityEntity> activities = activityDao.findByMid(mapId);
        if (activities != null) {
            ActivityService activityService = new ActivityServiceImpl();
            for (ActivityEntity activity : activities) {
                activityService.deleteActivity(activity.getId() + "");
            }
        }

        mapDao.delete(map);
        return "success";
    }
}
