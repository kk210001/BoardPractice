package com.spring.board.paging;

public class PageMakerImpl implements PageMaker{
    @Override
    public void pageSortDESC(int page,int range ,int listSize, int listCount) {

        Pagination pagination = new Pagination(page, range, listSize, listCount);

        pagination.setPageCount((int) Math.ceil(listCount/(double)listSize));
        pagination.setStartPage(listSize+1);
        pagination.setStartList((page-1) * listSize +1);
        int endList = page * listSize > listCount ? page * listSize : listCount;
        pagination.setEndList(endList);
    }
}
