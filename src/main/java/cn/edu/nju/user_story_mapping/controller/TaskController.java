package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.MapService;
import cn.edu.nju.user_story_mapping.service.TaskService;
import cn.edu.nju.user_story_mapping.vo.MapVO;
import cn.edu.nju.user_story_mapping.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/task/add_task")
    public TaskVO addTask(String aid, String title, String desc) {
        return taskService.addTask(aid, title, desc);
    }

    @GetMapping(value = "/task/get_task")
    public List<TaskVO> getTaskList(String aid) {
        return taskService.getTaskList(aid);
    }

    @PostMapping(value = "/task/delete_task")
    public String deleteTask(String tid) {
        return taskService.deleteTask(tid);
    }
}