package cn.wannengde.community.service;

import cn.wannengde.community.dto.QuestionDTO;
import cn.wannengde.community.mapper.QuestionMapper;
import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.Question;
import cn.wannengde.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
@author kbq
@date  2019/7/1 - 1:03
*/
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
