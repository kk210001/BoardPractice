package com.spring.board.paging;

public interface PageMaker {
    public Pagination pageSort(int page,int range ,int listSize,int listCount);
}
