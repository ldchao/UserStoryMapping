package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.ActivityService;
import cn.edu.nju.user_story_mapping.service.TaskService;
import cn.edu.nju.user_story_mapping.vo.ActivityVO;
import cn.edu.nju.user_story_mapping.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping(value = "/activity/add_activity")
    public ActivityVO addActivity(String mid, String title, String desc) {
        return activityService.addActivity(mid, title, desc);
    }

    @GetMapping(value = "/activity/get_activity")
    public List<ActivityVO> getActivityList(String mid) {
        return activityService.getActivityList(mid);
    }

    @PostMapping(value = "/activity/delete_activity")
    public String deleteActivity(String aid) {
        return activityService.deleteActivity(aid);
    }
}
