package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.StoryVO;

import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface StoryService {

    StoryVO addStory(String rid, String tid, String title, String desc, String state, int points);

    List<StoryVO> getStoryByTask(String tid);

    List<StoryVO> getStoryByRelease(String rid);

    StoryVO updateStory(String sid, String title, String desc, int points, String state, String tid, String rid);

    String deleteStory(String sid);
}
