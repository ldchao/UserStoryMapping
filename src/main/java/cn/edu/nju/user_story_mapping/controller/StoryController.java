package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.StoryService;
import cn.edu.nju.user_story_mapping.service.TaskService;
import cn.edu.nju.user_story_mapping.vo.StoryVO;
import cn.edu.nju.user_story_mapping.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping(value = "/story/add_story")
    public StoryVO addStory(String rid, String tid, String title, String desc, int points){
        return storyService.addStory(rid,tid,title,desc,"not start",points);
    }

    @GetMapping(value = "/story/get_story_task")
    public List<StoryVO> getStoryByTask(String tid){
        return storyService.getStoryByTask(tid);
    }

    @GetMapping(value = "/story/get_story_release")
    public List<StoryVO> getStoryByRelease(String rid){
        return storyService.getStoryByRelease(rid);
    }

    @PostMapping(value="/story/update_story")
    public StoryVO updateStory(String sid, String title, String desc, int points, String state, String tid, String rid){
        return storyService.updateStory(sid, title, desc, points, state, tid, rid);
    }

    @PostMapping(value = "/story/delete_story")
    public String deleteStory(String sid){
        return storyService.deleteStory(sid);
    }
}
