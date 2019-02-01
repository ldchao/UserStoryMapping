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
    public StoryVO addStory(String rid, String tid, String title, String desc, String state, int points) {
        StoryVO storyVO = new StoryVO();
        StoryEntity storyTemp = new StoryEntity();
        ReleaseEntity release = releaseDao.findOne(rid);
        TaskEntity task = taskDao.findOne(tid);
        ActivityEntity activity = activityDao.findOne(task.getAid() + "");
        if (!activity.getMid().equals(release.getMid())) {
            storyVO.setCode(0);
            return storyVO;
        }
        storyTemp.setRid(Integer.parseInt(rid));
        storyTemp.setTid(Integer.parseInt(tid));
        storyTemp.setTitle(title);
        storyTemp.setDescription(desc);
        storyTemp.setState(state);
        storyTemp.setStoryPoints(points);
        storyTemp.setCreateAt(new Timestamp(new Date().getTime()));

        storyDao.save(storyTemp);
        StoryEntity story = storyDao.findFirstByTidAndRidAndTitleAndDescription(tid, rid, title, desc);
        if (story == null) {
            storyVO.setCode(0);
            return storyVO;
        }

        storyVO = new StoryVO(story);
        storyVO.setSid(story.getId() + "");
        storyVO.setCode(1);
        return storyVO;
    }

    @Override
    public List<StoryVO> getStoryByTask(String tid) {
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
    public List<StoryVO> getStoryByRelease(String rid) {
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

    private List<StoryVO> EntitiesToVOs(List<StoryEntity> storiss) {
        List<StoryVO> storyVOS = new ArrayList<>();
        for (StoryEntity story : storiss) {
            StoryVO storyVO = new StoryVO(story);
            storyVO.setSid(story.getId() + "");
            storyVO.setCode(1);
            storyVOS.add(storyVO);
        }
        return storyVOS;
    }

    @Override
    public StoryVO updateStory(String sid, String title, String desc, int points, String state, String tid, String rid) {
        StoryEntity story = storyDao.findOne(sid);
        if (story == null) {
            StoryVO storyVO = new StoryVO();
            storyVO.setCode(0);
            return storyVO;
        }
        storyDao.delete(sid);
        return this.addStory(rid, tid, title, desc, state, points);
    }

    @Override
    public String deleteStory(String sid) {
        StoryEntity story = storyDao.findOne(sid);
        if (story == null) {
            return "fail";
        }
        storyDao.delete(sid);
        return "success";
    }
}
