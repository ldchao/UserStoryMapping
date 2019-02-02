package cn.edu.nju.user_story_mapping.controller;

import cn.edu.nju.user_story_mapping.service.ReleaseService;
import cn.edu.nju.user_story_mapping.vo.ReleaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @PostMapping(value = "/release/add_release")
    public ReleaseVO addRelease(Timestamp date, int mid) {
        return releaseService.addRelease(mid, date);
    }

    @GetMapping(value = "/release/get_release")
    public List<ReleaseVO> getReleaseList(int mid) {
        return releaseService.getReleaseList(mid);
    }

    @PostMapping(value = "/release/delete_release")
    public String deleteRelease(int rid) {
        return releaseService.deleteRelease(rid);
    }
}
