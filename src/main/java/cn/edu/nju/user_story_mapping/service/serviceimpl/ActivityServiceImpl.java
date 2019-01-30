package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.dao.MapDao;
import cn.edu.nju.user_story_mapping.dao.StoryDao;
import cn.edu.nju.user_story_mapping.dao.TaskDao;
import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import cn.edu.nju.user_story_mapping.service.ActivityService;
import cn.edu.nju.user_story_mapping.service.TaskService;
import cn.edu.nju.user_story_mapping.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    MapDao mapDao;

    @Autowired
    TaskDao taskDao;

    @Override
    public ActivityVO addActivity(String mid, String title, String desc) {
        MapEntity map = mapDao.findOne(mid);
        ActivityVO activityVO = new ActivityVO();
        if (map == null) {
            activityVO.setCode(0);
            return activityVO;
        }
        ActivityEntity activityTemp = new ActivityEntity();
        activityTemp.setMid(Integer.parseInt(mid));
        activityTemp.setTitle(title);
        activityTemp.setDescription(desc);
        activityDao.save(activityTemp);

        ActivityEntity activity = activityDao.findFirstByMidAndTitleAndDescription(mid, title, desc);
        if (activity == null) {
            activityVO.setCode(0);
            return activityVO;
        }
        activityVO = new ActivityVO(activity);
        activityVO.setAid(activity.getId() + "");
        activityVO.setCode(1);
        return activityVO;
    }

    @Override
    public List<ActivityVO> getActivityList(String mid) {
        List<ActivityEntity> activities = activityDao.findByMid(mid);
        ArrayList<ActivityVO> activityVOS = new ArrayList<>();
        if (activities == null || activities.size() == 0) {
            return activityVOS;
        }
        for (ActivityEntity activity : activities) {
            ActivityVO activityVO = new ActivityVO(activity);
            activityVO.setAid(activity.getId() + "");
            activityVO.setCode(1);
            activityVOS.add(activityVO);
        }
        return activityVOS;
    }

    @Override
    public String deleteActivity(String aid) {
        ActivityEntity activity = activityDao.findOne(aid);
        if (activity == null) {
            return "fail";
        }
        List<TaskEntity> tasks = taskDao.findByAid(aid);
        if (tasks != null) {
            TaskService taskService = new TaskServiceImpl();
            for (TaskEntity task : tasks) {
                taskService.deleteTask(task.getId()+"");
            }
            taskDao.delete(tasks);
        }
        activityDao.delete(activity);
        return "success";
    }
}
