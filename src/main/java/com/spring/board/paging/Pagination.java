package com.spring.board.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("pagination")
public class Pagination {
    private  int listSize;
    private int page;
    private int range;
    private int listCount;
    private int pageCount;
    private int startPage;
    private int startList;
    private int endList;

    public Pagination(int page, int range, int listCount) {
        this.listCount = listCount;
        this.page = page;
        this.range = range;

        this.listSize = 10;
        this.pageCount = (int) Math.ceil(listCount/listSize)+1; //올림
        this.startPage =( range - 1 ) * listSize + 1;
        this.startList = (page-1) * listSize +1;
        this.endList = page * listSize;
        if(this.endList > listCount){
            this.endList = listCount;
        }
//        this.startPage =( range - 1 ) * rangeSize + 1;
//        this.endList = listCount - (page-1) * 10;
//        if(this.endList > listCount){
//            this.endList = listCount;
//        }
//        this.startList = endPage - 9 > 0 ? endPage - 9:1;
    }
}
