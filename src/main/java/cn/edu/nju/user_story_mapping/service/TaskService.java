package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.TaskVO;

import java.util.List;

public interface TaskService {

    TaskVO addTask(int aid, String title, String desc);

    List<TaskVO> getTaskList(int aid);

    String deleteTask(int tid);
}
