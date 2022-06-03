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
        this.pageCount = (int) Math.ceil(listCount/(double)listSize); //올림
        this.startPage =( range - 1 ) * listSize + 1;
        this.startList =
                listCount - page * listSize +1 < 1 ? 1 : listCount - page * listSize +1;
        this.endList = listCount - ((page -1) * listSize) ;


        //전 환경에 쓰인 계산
//        this.startList = (page-1) * listSize +1;
//       this.endList = page * listSize;
//       this.endList = listCount - (page-1) * listSize;
//        this.startList = (page-1) * listSize +1;
//        this.endList = (pageCount - page + 1) * listSize;
//        if(this.endList > listCount){
//            this.endList = listCount;
//        }
    }
}
