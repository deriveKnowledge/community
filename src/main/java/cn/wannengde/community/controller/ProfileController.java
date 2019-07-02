package cn.wannengde.community.controller;

import cn.wannengde.community.dto.PageinationDTO;
import cn.wannengde.community.mapper.QuestionMapper;
import cn.wannengde.community.model.Question;
import cn.wannengde.community.model.User;
import cn.wannengde.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/*
@author kbq
@date  2019/7/1 - 16:23
*/
@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "5")Integer size,
                          HttpServletRequest request,
                          Model model){

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("pageination",new PageinationDTO());
            return "profile";
        }

        PageinationDTO pageinationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pageination",pageinationDTO);
        return "profile";
    }
}
