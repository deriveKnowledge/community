package cn.wannengde.community.dto;

import lombok.Data;

/*
@author kbq
@date  2019/6/29 - 18:09
*/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
