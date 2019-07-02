package cn.wannengde.community.controller;

import cn.wannengde.community.dto.PageinationDTO;
import cn.wannengde.community.dto.QuestionDTO;
import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.User;
import cn.wannengde.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
@author kbq
@date  2019/6/29 - 16:01
*/
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size,
                        Model model){
        PageinationDTO pageination = questionService.list(page,size);
        model.addAttribute("pageination",pageination);
        return "index";
    }
}
