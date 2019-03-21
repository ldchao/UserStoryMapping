package cn.edu.nju.user_story_mapping.service.serviceimpl;

import cn.edu.nju.user_story_mapping.dao.EditLogDao;
import cn.edu.nju.user_story_mapping.dao.MapDao;
import cn.edu.nju.user_story_mapping.dao.UserDao;
import cn.edu.nju.user_story_mapping.entity.EditLogEntity;
import cn.edu.nju.user_story_mapping.entity.MapEntity;
import cn.edu.nju.user_story_mapping.entity.UserEntity;
import cn.edu.nju.user_story_mapping.service.EditLogService;
import cn.edu.nju.user_story_mapping.vo.EditLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditLogServiceImpl implements EditLogService {

    @Autowired
    private EditLogDao editLogDao;

    @Autowired
    private MapDao mapDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<EditLogVO> getEditLogByMid(int mid) {
        MapEntity map = mapDao.findOne(mid);
        List<EditLogVO> editLogVOS = new ArrayList<>();
        if (map == null) {
            return editLogVOS;
        }
        List<EditLogEntity> editLogs = editLogDao.findByMid(mid);
        return this.EntitiesToVOs(editLogs);
    }

    @Override
    public List<EditLogVO> getEditLogByUid(int uid) {
        UserEntity user = userDao.findOne(uid);
        List<EditLogVO> editLogVOS = new ArrayList<>();
        if (user == null) {
            return editLogVOS;
        }
        List<EditLogEntity> editLogs = editLogDao.findByMid(uid);
        return this.EntitiesToVOs(editLogs);
    }

    private ArrayList<EditLogVO> EntitiesToVOs(List<EditLogEntity> editLogs) {
        ArrayList<EditLogVO> editLogVOS = new ArrayList<>();
        if (editLogs == null) {
            return editLogVOS;
        }
        for (EditLogEntity editLog : editLogs) {
            editLogVOS.add(new EditLogVO(editLog));
        }
        return editLogVOS;
    }

}
