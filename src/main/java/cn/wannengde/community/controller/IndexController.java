package cn.wannengde.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
@author kbq
@date  2019/6/29 - 16:01
*/
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
