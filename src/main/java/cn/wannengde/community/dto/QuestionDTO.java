package cn.wannengde.community.dto;

import cn.wannengde.community.model.User;
import lombok.Data;

/*
@author kbq
@date  2019/7/1 - 0:59
*/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Integer unlikeCount;
    private String tag;
    private User user;
}
