package cn.wannengde.community.mapper;

import cn.wannengde.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
@author kbq
@date  2019/6/30 - 9:55
*/
@Mapper
public interface UserMapper {

    @Insert("insert into user(id,account_id,name,token,gmt_create,gmt_modified,avatar_url) " +
            "values (#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
