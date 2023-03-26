package com.spring.board.paging;

import com.spring.board.board.dao.BoardDAO;
import org.springframework.stereotype.Component;

@Component("pageMaker")
public class PageMakerImpl implements PageMaker{

    private final BoardDAO boardDAO;

    public PageMakerImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    //borad.xml ORDER BY articleNO DESC
    @Override
    public Pagination pageSort(int page ,int listSize, int listCount) {



        Pagination pagination = new Pagination();
        if(page < 1){
            page = 1;
        }
        pagination.setPage(page);
        pagination.setListSize(listSize);
        pagination.setListCount(listCount);
        pagination.setRangeSize(10);


        //페이지 갯수 =  모든 글 수 / 한 페이지에 존재하는 게시글 수
        int pageCount = (int) Math.ceil(listCount/(double)listSize);


        pagination.setPageCount(pageCount);
        int rangeSize = pagination.getRangeSize();

        //page = 현재 페이지, rangeSize = 한 화면에 담긴 페이지 갯수 default = 10
        pagination.setStartPage(((page-1 )/ rangeSize) * rangeSize +1);
        //startList = 페이지 시작 글
        pagination.setStartList((page-1) * listSize +1);

        //endList = 페이지 끝 글
        int endList = page * listSize;
        //게시글 갯수가 10개를 안넘어갈 경우 총 페이지 수로 마지막 글 결정
        pagination.setEndList(endList < listCount ? endList : listCount);

        // 시작 페이지 + 9 가 페이지 갯수보다 작다면
        int startPage = pagination.getStartPage();
        int tmpPage =startPage + rangeSize-1;
        pagination.setEndPage(tmpPage < pageCount ? tmpPage : pageCount);

        return pagination;

    }

    @Override
    public Pagination paging(int page, int listSize, String type, String keyword) throws Exception {

        int boardAllCount = boardDAO.getBoardAllCount();
        Pagination pagination = pageSort(page, listSize, boardAllCount);
        pagination.setKeyword(keyword);
        pagination.setType(type);
        return pagination;
    }
}
