package com.spring.board.paging;

public interface PageMaker {
    public Pagination pageSort(int page ,int listSize,int listCount);

    Pagination paging(int page, int listSize, String type, String keyword) throws Exception;
}
