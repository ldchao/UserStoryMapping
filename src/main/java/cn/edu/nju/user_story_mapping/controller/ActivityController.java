package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.ActivityService;
import cn.edu.nju.user_story_mapping.vo.ActivityVO;
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
    public ActivityVO addActivity(int mid, String title, String desc) {
        return activityService.addActivity(mid, title, desc);
    }

    @GetMapping(value = "/activity/get_activity")
    public List<ActivityVO> getActivityList(int mid) {
        return activityService.getActivityList(mid);
    }

    @PostMapping(value = "/activity/delete_activity")
    public String deleteActivity(int aid) {
        return activityService.deleteActivity(aid);
    }
}
