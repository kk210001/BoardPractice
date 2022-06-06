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
    private int range;

//    public Pagination(int page,int range, int listSize, int listCount) {
//        this.range = range;
//        this.listCount = listCount;
//        this.page = page;
//        this.listSize = listSize;
//        this.rangeSize=10;
//        this.pageCount = (int) Math.ceil(listCount/(double)listSize); //올림
//        this.startPage = (range-1) * listSize + 1;
//
//        this.startList = (page-1) * listSize +1 ;
//        int tmpList = page * listSize;
//        this.endList = tmpList < listCount ? tmpList : listCount;
//
//        int tmpPage = range * rangeSize;
//        this.endPage = tmpPage < pageCount ? tmpPage : pageCount;


//        this.startList =
//                listCount - page * listSize +1 < 1 ? 1 : listCount - page * listSize +1;
//        this.endList = listCount - ((page -1) * listSize) ;

//        this.startPage =( range - 1 ) * rangeSize + 1;
//        this.endList = listCount - (page-1) * 10;
//        if(this.endList > listCount){
//            this.endList = listCount;
//        }
//        this.startList = endPage - 9 > 0 ? endPage - 9:1;

}
