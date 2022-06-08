package com.spring.board.paging;

import org.springframework.stereotype.Component;

@Component("pageMaker")
public class PageMakerImpl implements PageMaker{
    //borad.xml ORDER BY articleNO DESC
    @Override
    public Pagination pageSort(int page ,int listSize, int listCount) {

//        Pagination pagination = new Pagination(page, range, listSize, listCount);
        Pagination pagination = new Pagination();
        if(page < 1){
            page = 1;
        }
        pagination.setPage(page);
        pagination.setListSize(listSize);
        pagination.setListCount(listCount);
        pagination.setRangeSize(10);
        int pageCount = (int) Math.ceil(listCount/(double)listSize);

        pagination.setPageCount(pageCount);
//        pagination.setStartPage((range-1) * listSize + 1);
        int rangeSize = pagination.getRangeSize();
        pagination.setStartPage(((page-1 )/ rangeSize) * rangeSize +1);
        pagination.setStartList((page-1) * listSize +1);

        int endList = page * listSize;
        pagination.setEndList(endList < listCount ? endList : listCount);

//        int tmpPage = range * pagination.getRangeSize();
        int startPage = pagination.getStartPage();
        int tmpPage =startPage + rangeSize-1;
        pagination.setEndPage(tmpPage < pageCount ? tmpPage : pageCount);

        return pagination;

    }
}
