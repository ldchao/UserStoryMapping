package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.dao.ReleaseDao;
import cn.edu.nju.user_story_mapping.dao.StoryDao;
import cn.edu.nju.user_story_mapping.dao.TaskDao;
import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import cn.edu.nju.user_story_mapping.entity.StoryEntity;
import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import cn.edu.nju.user_story_mapping.service.StoryService;
import cn.edu.nju.user_story_mapping.vo.StoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private ReleaseDao releaseDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private StoryDao storyDao;

    @Override
    public StoryVO addStory(int rid, int tid, String title, String desc, String state, int points) {
//        StoryVO storyVO = new StoryVO();
        StoryEntity story = new StoryEntity();
//        ReleaseEntity release = releaseDao.findOne(rid);
//        TaskEntity task = taskDao.findOne(tid);
//        ActivityEntity activity = activityDao.findOne(task.getAid());
//        if (!activity.getMid().equals(release.getMid())) {
//            return storyVO;
//        }

        story.setRid(rid);
        story.setTid(tid);
        story.setTitle(title);
        story.setDescription(desc);
        story.setState(state);
        story.setStoryPoints(points);
        story.setCreateAt(new Timestamp(new Date().getTime()));
        storyDao.save(story);

        return new StoryVO(story);
    }

    @Override
    public List<StoryVO> getStoryByTask(int tid) {
        List<StoryVO> storyVOS = new ArrayList<>();
        TaskEntity task = taskDao.findOne(tid);
        if (task == null) {
            return storyVOS;
        }
        List<StoryEntity> stories = storyDao.findByTid(tid);
        if (stories == null || stories.size() == 0) {
            return storyVOS;
        }

        return this.EntitiesToVOs(stories);
    }

    @Override
    public List<StoryVO> getStoryByRelease(int rid) {
        List<StoryVO> storyVOS = new ArrayList<>();
        ReleaseEntity release = releaseDao.findOne(rid);
        if (release == null) {
            return storyVOS;
        }
        List<StoryEntity> stories = storyDao.findByRid(rid);
        if (stories == null || stories.size() == 0) {
            return storyVOS;
        }

        return this.EntitiesToVOs(stories);
    }

    private List<StoryVO> EntitiesToVOs(List<StoryEntity> stories) {
        List<StoryVO> storyVOS = new ArrayList<>();
        for (StoryEntity story : stories) {
            storyVOS.add(new StoryVO(story));
        }
        return storyVOS;
    }

    @Override
    public StoryVO updateStory(int sid, int tid, int rid, String title, String desc, int points, String state) {
        StoryEntity story = storyDao.findOne(sid);
        if (story == null) {
            return new StoryVO();
        }
        storyDao.delete(sid);
        return this.addStory(rid, tid, title, desc, state, points);
    }

    @Override
    public String deleteStory(int sid) {
        StoryEntity story = storyDao.findOne(sid);
        if (story == null) {
            return "fail";
        }
        storyDao.delete(sid);
        return "success";
    }
}
