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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
    public ReleaseVO addRelease(int mid, Timestamp startAt, Timestamp endAt) {
        MapEntity map = mapDao.findOne(mid);
        if (map == null) {
            return new ReleaseVO();
        }

        ReleaseEntity release = new ReleaseEntity();
        release.setStartAt(startAt);
        release.setEndAt(endAt);
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

    @Override
    public ReleaseVO endRelease(int rid) {
        ReleaseEntity release = releaseDao.findOne(rid);
        if (release == null) {
            return new ReleaseVO();
        }
        Timestamp startTime = release.getStartAt();
        long currentTime = System.currentTimeMillis();
        if (startTime.getTime() >= currentTime) {
            return new ReleaseVO();
        }
        release.setEndAt(new Timestamp(currentTime));
        releaseDao.save(release);
        return new ReleaseVO(release);
    }

    @Override
    public LinkedHashMap<String, Integer> datePointPair(int rid) {
        LinkedHashMap<String, Integer> pairs = new LinkedHashMap<>();
        ReleaseEntity release = releaseDao.findOne(rid);
        if (release == null) {
            return pairs;
        }

        List<StoryEntity> stories = storyDao.findByRid(rid);
        int pointSum = 0;
        for (StoryEntity story : stories) {
            pointSum += story.getStoryPoints();
        }

        Date startDate = new Date(release.getStartAt().getTime());
        Date endDate = new Date(release.getEndAt().getTime());
        Date today = new Date(System.currentTimeMillis());
        if (endDate.after(today)) {
            endDate = today;
        }

        ArrayList<Date> dates = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();
        dates.add(startDate);
        points.add(pointSum);
        Date next = this.nextDay(startDate);
        while (next.before(endDate)) {
            dates.add(next);
            points.add(pointSum);
            next = nextDay(next);
        }

        for (StoryEntity story : stories) {
            if (story.getState().equals("done")) {
                Date doneTime = story.getUpdateAt();
                for (int i = 0; i < dates.size(); i++) {
                    if (dates.get(i).after(doneTime)) {
                        int currentPoints = points.get(i);
                        currentPoints -= story.getStoryPoints();
                        points.set(i, currentPoints);
                    }
                }
            }
        }


        for (int i = 0; i < dates.size(); i++) {

            String s = DateFormat.getDateInstance(DateFormat.MEDIUM).format(dates.get(i));
            pairs.put(s, points.get(i));

        }

        return pairs;
    }

    private Date nextDay(Date date) {
        return new Date(date.getTime() + 24 * 3600 * 1000);
    }
}
