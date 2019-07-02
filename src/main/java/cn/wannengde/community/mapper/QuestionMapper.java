package cn.wannengde.community.mapper;

import cn.wannengde.community.model.Question;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from question limit #{offset},#{size}")
    List<Question> findPage(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> findPageByUserId(@Param("userId")Integer userId,@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param("userId")Integer userId);

    @Select("select * from question where id = #{id}")
    Question findById(@Param("id")Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}
