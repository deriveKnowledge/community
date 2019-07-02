package cn.wannengde.community.controller;

import cn.wannengde.community.mapper.QuestionMapper;
import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.Question;
import cn.wannengde.community.model.User;
import cn.wannengde.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
@author kbq
@date  2019/6/30 - 13:05
*/
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish/{id}")
    public String publish(Model model,
                          @PathVariable("id")Integer id){

        Question question = questionMapper.findById(id);
        if(question == null){
            return "redirect:/publish";
        }
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            @RequestParam(value = "id",required = false)Integer id,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag); //用于报错时返回页面

        if(title==null || title.equals("")){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        if(description==null || description.equals("")){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null || tag.equals("")){
            model.addAttribute("error","问题标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录，添加失败！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createOrupdate(question);
        return "redirect:/";
    }
}
