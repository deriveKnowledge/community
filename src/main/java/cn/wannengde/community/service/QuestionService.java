package cn.wannengde.community.service;

import cn.wannengde.community.dto.PageinationDTO;
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

    public PageinationDTO list(Integer page,Integer size){

        Integer totalCount = questionMapper.count();
        PageinationDTO pageinationDTO = new PageinationDTO();
        pageinationDTO.setPageination(totalCount,page,size);

        Integer offset = pageinationDTO.getCurrentSize()*(pageinationDTO.getCurrentPage() - 1);
        List<Question> questions = questionMapper.findPage(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            if(user != null)
                questionDTO.setUser(user);
            else
                questionDTO.setUser(new User());
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestionDTOList(questionDTOList);
        return pageinationDTO;
    }

    //根据用户id，获取用户所发布的问题
    public PageinationDTO list(Integer id, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByUserId(id);
        PageinationDTO pageinationDTO = new PageinationDTO();
        pageinationDTO.setPageination(totalCount,page,size);

        Integer offset = pageinationDTO.getCurrentSize()*(pageinationDTO.getCurrentPage() - 1);
        List<Question> questions = questionMapper.findPageByUserId(id,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        User user = userMapper.findById(id);
        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question,questionDTO);
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestionDTOList(questionDTOList);
        return pageinationDTO;
    }

    //根据问题id，获取问题
    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.findById(id);
        User user = new User();
        if(question != null){
            BeanUtils.copyProperties(question,questionDTO);
            user = userMapper.findById(question.getCreator());
        }
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrupdate(Question question) {
        Question byId = null;
        if(question.getId() != null)
            byId = questionMapper.findById(question.getId());
        if(byId == null){
            questionMapper.create(question);
        }else{
            questionMapper.update(question);
        }
    }
}
