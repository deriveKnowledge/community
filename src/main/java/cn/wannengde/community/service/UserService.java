package cn.wannengde.community.service;

import cn.wannengde.community.mapper.UserMapper;
import cn.wannengde.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@author kbq
@date  2019/7/2 - 1:25
*/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void updateOrCreate(User user) {

        User byAccountId = userMapper.findByAccountId(user.getAccountId());
        if(byAccountId == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            byAccountId.setAvatarUrl(user.getAvatarUrl());
            byAccountId.setToken(user.getToken());
            byAccountId.setName(user.getName());
            byAccountId.setGmtModified(System.currentTimeMillis());
            userMapper.update(byAccountId);
        }
    }
}
