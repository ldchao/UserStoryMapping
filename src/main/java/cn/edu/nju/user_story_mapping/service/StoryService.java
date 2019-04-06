package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.StoryVO;

import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface StoryService {

    StoryVO addStory(int rid, int tid, String title, String desc, String state, int points);

    List<StoryVO> getStoryByTask(int tid);

    List<StoryVO> getStoryByRelease(int rid);

    StoryVO updateStory(int sid, String title, String desc, int points, String state);

    String deleteStory(int sid);
}
