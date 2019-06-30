package cn.wannengde.community.model;

import lombok.Data;

/*
@author kbq
@date  2019/6/30 - 21:02
*/
@Data
public class Question {
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

}
