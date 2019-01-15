package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.ActivityDao;
import cn.edu.nju.user_story_mapping.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ldchao on 2019/1/15.
 */
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    ActivityDao activityDao;

    @Override
    public String addActivity() {
        return null;
    }


}
