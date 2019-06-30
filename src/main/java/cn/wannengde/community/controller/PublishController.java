package cn.wannengde.community.controller;

import cn.wannengde.community.mapper.QuestionMapper;
import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.Question;
import cn.wannengde.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "publish";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                request.getSession().setAttribute("user",user);
            }
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
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

        Cookie[] cookies = request.getCookies();
        User user = null;
        //foreach循环时如果集合为null，会报空指针异常
        if(cookies == null){
            model.addAttribute("error","用户未登录，添加失败！");
            return "publish";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录，添加失败！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
