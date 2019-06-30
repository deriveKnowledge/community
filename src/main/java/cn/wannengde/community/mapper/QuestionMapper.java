package cn.wannengde.community.mapper;

import cn.wannengde.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
@author kbq
@date  2019/6/30 - 21:05
*/
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,tag,creator,gmt_create,gmt_modified)" +
            "values(#{title},#{description},#{tag},#{creator},#{gmtCreate},#{gmtModified})")
    void create(Question question);

    @Select("select * from question")
    List<Question> findAll();
}
