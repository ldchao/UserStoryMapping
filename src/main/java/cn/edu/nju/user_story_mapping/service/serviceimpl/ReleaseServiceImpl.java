package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.MapDao;
import cn.edu.nju.user_story_mapping.dao.ReleaseDao;
import cn.edu.nju.user_story_mapping.dao.StoryDao;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import cn.edu.nju.user_story_mapping.entity.StoryEntity;
import cn.edu.nju.user_story_mapping.service.ReleaseService;
import cn.edu.nju.user_story_mapping.vo.ReleaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private MapDao mapDao;

    @Autowired
    private ReleaseDao releaseDao;

    @Autowired
    private StoryDao storyDao;

    @Override
    public ReleaseVO addRelease(Timestamp date, String mid) {
        MapEntity map = mapDao.findOne(mid);
        ReleaseVO releaseVO = new ReleaseVO();
        if (map == null) {
            releaseVO.setCode(0);
            return releaseVO;
        }

        ReleaseEntity releaseTemp = new ReleaseEntity();
        releaseTemp.setDate(date);
        releaseTemp.setMid(Integer.parseInt(mid));
        releaseDao.save(releaseTemp);

        ReleaseEntity release = releaseDao.findFirstByMidAndDate(mid, date);
        if (release == null) {
            releaseVO.setCode(0);
            return releaseVO;
        }
        releaseVO = new ReleaseVO(release);
        releaseVO.setRid(release.getId() + "");
        releaseVO.setCode(1);
        return releaseVO;
    }

    @Override
    public List<ReleaseVO> getReleaseList(String mid) {
        List<ReleaseVO> releaseVOS = new ArrayList<>();
        List<ReleaseEntity> releases = releaseDao.findByMid(mid);
        if (releases == null || releases.size() == 0) {
            return releaseVOS;
        }
        for (ReleaseEntity releaseEntity : releases) {
            ReleaseVO releaseVO = new ReleaseVO(releaseEntity);
            releaseVO.setRid(releaseEntity.getId() + "");
            releaseVO.setCode(1);
            releaseVOS.add(releaseVO);
        }
        return releaseVOS;
    }

    @Override
    public String deleteRelease(String rid) {
        ReleaseEntity release = releaseDao.findOne(rid);
        if (release == null) {
            return "fail";
        }
        List<StoryEntity> stories = storyDao.findByRid(rid);
        if (stories != null) {
            storyDao.delete(stories);
        }
        releaseDao.delete(release);
        return "success";
    }
}
