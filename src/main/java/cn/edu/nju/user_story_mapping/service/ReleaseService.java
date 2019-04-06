package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.ReleaseVO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface ReleaseService {

    ReleaseVO addRelease(int mid, Timestamp startAt,Timestamp endAt);

    List<ReleaseVO> getReleaseList(int mid);

    String deleteRelease(int rid);

    ReleaseVO endRelease(int rid);

    LinkedHashMap<String,Integer> datePointPair(int rid);
}
