package cn.edu.nju.user_story_mapping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ldchao on 2018/5/7.
 */
@Controller
public class UrlController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
