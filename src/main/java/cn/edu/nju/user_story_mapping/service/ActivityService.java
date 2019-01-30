package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.ActivityVO;

import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface ActivityService {

    ActivityVO addActivity(String mid, String title, String desc);

    List<ActivityVO> getActivityList(String mid);

    String deleteActivity(String aid);
}
