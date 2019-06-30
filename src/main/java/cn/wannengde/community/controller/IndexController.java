package cn.wannengde.community.controller;

import cn.wannengde.community.dto.QuestionDTO;
import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.User;
import cn.wannengde.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
                        Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "index";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                request.getSession().setAttribute("user",user);
            }
        }

        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
