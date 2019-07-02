package cn.wannengde.community.dto;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
@author kbq
@date  2019/7/1 - 12:36
*/
@Data
public class PageinationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer currentPage;
    private Integer totalPage;
    private Integer currentSize;
    private List<Integer> pages = new ArrayList<>();;


    public void setPageination(Integer totalCount, Integer page, Integer size) {

        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size+1;
        }
        if(totalPage == 0){
            totalPage = 1;
        }
        if(page >= totalPage){
            page = totalPage;
        }
        if(page < 1){
            page = 1;
        }
        if(size < 1){
            size = 5;
        }

        currentSize = size;
        currentPage = page;
        for(int i = 1 ; i <= 3 ;i++){
            if(page - i >0){
                pages.add(page-i);
            }
            if(page + i <= totalPage){
                pages.add(page+i);
            }
        }
        pages.add(page);
        Collections.sort(pages);
        //是否展现前一页
        if(page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }

        //是否展现后一页
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
