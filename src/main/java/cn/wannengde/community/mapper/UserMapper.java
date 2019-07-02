package cn.wannengde.community.mapper;

import cn.wannengde.community.model.User;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("UPDATE user SET name=#{name},gmt_modified=#{gmtModified},token=#{token},avatar_url=#{avatarUrl}")
    void update(User byAccountId);
}
