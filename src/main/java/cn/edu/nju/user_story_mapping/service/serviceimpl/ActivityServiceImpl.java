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

    @Autowired
    TaskService taskService;

    @Override
    public ActivityVO addActivity(int mid, String title, String desc) {
        MapEntity map = mapDao.findOne(mid);
        if (map == null) {
            return new ActivityVO();
        }
        ActivityEntity activity = new ActivityEntity();
        activity.setMid(mid);
        activity.setTitle(title);
        activity.setDescription(desc);
        activityDao.save(activity);

        return new ActivityVO(activity);
    }

    @Override
    public List<ActivityVO> getActivityList(int mid) {
        List<ActivityEntity> activities = activityDao.findByMid(mid);
        ArrayList<ActivityVO> activityVOS = new ArrayList<>();
        if (activities == null || activities.size() == 0) {
            return activityVOS;
        }
        for (ActivityEntity activity : activities) {
            activityVOS.add(new ActivityVO(activity));
        }
        return activityVOS;
    }

    @Override
    public String deleteActivity(int aid) {
        ActivityEntity activity = activityDao.findOne(aid);
        if (activity == null) {
            return "fail";
        }
        List<TaskEntity> tasks = taskDao.findByAid(aid);
        if (tasks != null) {
            for (TaskEntity task : tasks) {
                taskService.deleteTask(task.getId());
            }
            taskDao.delete(tasks);
        }
        activityDao.delete(activity);
        return "success";
    }
}
