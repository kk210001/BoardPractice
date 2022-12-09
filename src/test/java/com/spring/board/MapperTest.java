package com.spring.board;

import com.spring.board.board.controller.BoardController;
import com.spring.board.board.dao.BoardDAO;
import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.board.service.BoardService;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import org.apache.ibatis.session.SqlSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {


    private final BoardDAO boardDAO;
    private final SqlSession sqlSession;
    private final BoardController boardController;
    private final BoardService boardService;
    private final PageMaker pageMaker;

//    @Autowired
//    public MapperTest(BoardDAO boardDAO, SqlSession sqlSession, BoardController boardController, BoardService boardService) {
//        this.boardDAO = boardDAO;
//        this.sqlSession = sqlSession;
//        this.boardController = boardController;
//        this.boardService = boardService;
//    }
@Autowired
public MapperTest(BoardDAO boardDAO, SqlSession sqlSession, BoardController boardController, BoardService boardService, PageMaker pageMaker) {
    this.boardDAO = boardDAO;
    this.sqlSession = sqlSession;
    this.boardController = boardController;
    this.boardService = boardService;
    this.pageMaker = pageMaker;
}


    //@Test
    public void testGetTitle() throws Exception {

        Pagination pagination = pageMaker.pageSort(1, 10, 40);
        List<ArticleDTO> list = sqlSession.selectList("selectAllArticlesList", pagination);
        for (ArticleDTO ArticleDTO : list) {
            System.out.println(ArticleDTO.getTitle());

        }
        ArticleDTO ArticleDTO = new ArticleDTO();
        Map<String, String> articleMap = new HashMap<>();
        for (int i = 70; i < 500; i++) {
            articleMap.put("id", (i + 50) + "");
            articleMap.put("title", i + "");
            articleMap.put("content", i + "");
            boardDAO.insertNewArticle(articleMap);
        }
    }

    @Test
    public void getBoard() throws Exception{
        ArticleDTO ArticleDTO = new ArticleDTO();
        Map<String, String> articleMap = new HashMap<>();
        articleMap.put("id", "insertTest");
        articleMap.put("title", "Title");
        articleMap.put("content", "content");
        boardDAO.insertNewArticle(articleMap);
        ArticleDTO result = boardDAO.selectArticle(510);
        Assertions.assertThat(result.getTitle()).isEqualTo(articleMap.get("title"));
    }



}
