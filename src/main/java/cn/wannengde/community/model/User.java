package cn.wannengde.community.model;

/*
@author kbq
@date  2019/6/30 - 9:53
*/

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
