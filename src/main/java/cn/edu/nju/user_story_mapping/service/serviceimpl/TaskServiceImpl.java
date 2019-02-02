package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.dao.StoryDao;
import cn.edu.nju.user_story_mapping.dao.TaskDao;
import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import cn.edu.nju.user_story_mapping.entity.StoryEntity;
import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import cn.edu.nju.user_story_mapping.service.TaskService;
import cn.edu.nju.user_story_mapping.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private StoryDao storyDao;

    @Override
    public TaskVO addTask(int aid, String title, String desc) {
        ActivityEntity activity = activityDao.findOne(aid);
        TaskVO taskVO = new TaskVO();
        if (activity == null) {
            taskVO.setCode(0);
            return taskVO;
        }

        TaskEntity task = new TaskEntity();
        task.setAid(aid);
        task.setTitle(title);
        task.setDescription(desc);
        taskDao.save(task);

        return new TaskVO(task);
    }

    @Override
    public List<TaskVO> getTaskList(int aid) {
        List<TaskVO> taskVOS = new ArrayList<>();
        List<TaskEntity> tasks = taskDao.findByAid(aid);
        if (tasks == null || tasks.size() == 0) {
            return taskVOS;
        }
        for (TaskEntity task : tasks) {
            taskVOS.add(new TaskVO(task));
        }
        return taskVOS;
    }

    @Override
    public String deleteTask(int tid) {
        TaskEntity task = taskDao.findOne(tid);
        if (task == null) {
            return "fail";
        }
        List<StoryEntity> stories = storyDao.findByTid(tid);
        if (stories != null) {
            storyDao.delete(stories);
        }
        taskDao.delete(task);
        return null;
    }
}
