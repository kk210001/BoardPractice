package com.spring.board.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("pagination")
public class Pagination {
    private  int listSize;
    private  int rangeSize;
    private int page;
    private int listCount;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int startList;
    private int endList;

    //검색 필터
    private String type; // title, content, id
    private String keyword; // 검색 내용


}
