package com.spring.board.board.service;

import com.spring.board.board.dao.BoardDAO;
import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
@Service("boardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {



    private final BoardDAO boardDAO;
    private final PageMaker pageMaker;

    @Override
    public List<ArticleDTO> listArticles(Pagination pagination) throws Exception {
        List<ArticleDTO> articlesList = boardDAO.selectAllArticlesList(pagination);
        return articlesList;
    }

    @Override
    public Pagination paging(int page, int listSize, String type, String keyword) throws Exception {

        int boardAllCount = boardDAO.getBoardAllCount();
        Pagination pagination = pageMaker.pageSort(page, listSize, boardAllCount);
        pagination.setKeyword(keyword);
        pagination.setType(type);
        return pagination;
    }

    @Override
    public void addNewArticle(Map articleMap) throws Exception {
        int articleNO = boardDAO.selectNewArticleNO();
        articleMap.put("articleNO", articleNO);
        boardDAO.insertNewArticle(articleMap);
    }


    @Override
    public Map viewArticle(int articleNO) throws Exception {
        Map articleMap = new HashMap();
        boardDAO.addViewCount(articleNO);
        ArticleDTO ArticleDTO = boardDAO.selectArticle(articleNO);
        articleMap.put("article", ArticleDTO);
        return articleMap;
    }


    @Override
    public void modArticle(Map articleMap) throws Exception {
        boardDAO.updateArticle(articleMap);
    }

    @Override
    public void removeArticle(int articleNO) throws Exception {
        log.info("service layer {}번 글 삭제", articleNO);
        boardDAO.deleteArticle(articleNO);
    }



}
