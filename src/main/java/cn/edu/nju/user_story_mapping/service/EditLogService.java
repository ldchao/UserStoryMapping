package cn.edu.nju.user_story_mapping.service;

import cn.edu.nju.user_story_mapping.vo.EditLogVO;

import java.util.List;

public interface EditLogService {

    List<EditLogVO> getEditLogByUid(int uid);

    List<EditLogVO> getEditLogByMid(int mid);

    EditLogVO addEditLog(int uid, int mid, String type, int itemId, String desc);
}
