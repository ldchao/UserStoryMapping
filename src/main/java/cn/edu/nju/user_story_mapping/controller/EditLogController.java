package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.EditLogService;
import cn.edu.nju.user_story_mapping.service.UserService;
import cn.edu.nju.user_story_mapping.vo.EditLogVO;
import cn.edu.nju.user_story_mapping.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by ldchao on 2017/10/15.
 */
@RestController
@CrossOrigin
public class EditLogController {

    private final EditLogService editLogService;

    @Autowired
    public EditLogController(EditLogService editLogService) {
        this.editLogService = editLogService;
    }

    @GetMapping(value = "/editlog/getEditLogByUser")
    public List<EditLogVO> getEditLogByUser(int uid) {
        return editLogService.getEditLogByUid(uid);
    }

    @GetMapping(value = "/editlog/getEditLogByMap")
    public List<EditLogVO> getEditLogByMap(int mid) {
        return editLogService.getEditLogByMid(mid);
    }
    
}
