package cn.wannengde.community.dto;

import lombok.Data;

/*
@author kbq
@date  2019/6/29 - 18:43
*/
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
