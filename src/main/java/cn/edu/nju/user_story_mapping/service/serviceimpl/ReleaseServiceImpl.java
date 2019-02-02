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
    public ReleaseVO addRelease(int mid, Timestamp date) {
        MapEntity map = mapDao.findOne(mid);
        if (map == null) {
            return new ReleaseVO();
        }

        ReleaseEntity release = new ReleaseEntity();
        release.setDate(date);
        release.setMid(mid);
        releaseDao.save(release);

        return new ReleaseVO(release);
    }

    @Override
    public List<ReleaseVO> getReleaseList(int mid) {
        List<ReleaseVO> releaseVOS = new ArrayList<>();
        List<ReleaseEntity> releases = releaseDao.findByMid(mid);
        if (releases == null || releases.size() == 0) {
            return releaseVOS;
        }
        for (ReleaseEntity releaseEntity : releases) {
            releaseVOS.add(new ReleaseVO(releaseEntity));
        }
        return releaseVOS;
    }

    @Override
    public String deleteRelease(int rid) {
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
