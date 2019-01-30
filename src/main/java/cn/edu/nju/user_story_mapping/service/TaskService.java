package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.TaskVO;

import java.util.List;

public interface TaskService {

    TaskVO addTask(String aid, String title, String desc);

    List<TaskVO> getTaskList(String aid);

    String deleteTask(String tid);
}
