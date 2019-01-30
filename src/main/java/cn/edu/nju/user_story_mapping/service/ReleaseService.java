package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.ActivityVO;
import cn.edu.nju.user_story_mapping.vo.ReleaseVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface ReleaseService {

    ReleaseVO addRelease(Timestamp date, String mid);

    List<ReleaseVO> getReleaseList(String mid);

    String deleteRelease(String rid);
}
