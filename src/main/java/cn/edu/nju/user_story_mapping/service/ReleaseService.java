package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.ReleaseVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2019/1/15.
 */
public interface ReleaseService {

    ReleaseVO addRelease(int mid, Timestamp date);

    List<ReleaseVO> getReleaseList(int mid);

    String deleteRelease(int rid);
}
