package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.StoryService;
import cn.edu.nju.user_story_mapping.vo.StoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true")
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping(value = "/story/add_story")
    public StoryVO addStory(int rid, int tid, String title, String desc, int points) {
        return storyService.addStory(rid, tid, title, desc, "not start", points);
    }

    @GetMapping(value = "/story/get_story_task")
    public List<StoryVO> getStoryByTask(int tid) {
        return storyService.getStoryByTask(tid);
    }

    @GetMapping(value = "/story/get_story_release")
    public List<StoryVO> getStoryByRelease(int rid) {
        return storyService.getStoryByRelease(rid);
    }

    @PostMapping(value = "/story/update_story")
    public StoryVO updateStory(int sid, int tid, int rid, String title, String desc, int points, String state) {
        return storyService.updateStory(sid, tid, rid, title, desc, points, state);
    }

    @PostMapping(value = "/story/delete_story")
    public String deleteStory(int sid) {
        return storyService.deleteStory(sid);
    }
}
