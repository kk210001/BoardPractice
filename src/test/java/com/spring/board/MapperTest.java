package com.spring.board;

import com.spring.board.board.controller.BoardController;
import com.spring.board.board.dao.BoardDAO;
import com.spring.board.board.dao.BoardDAOImpl;
import com.spring.board.board.service.BoardService;
import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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


    @Test
    public void testGetTitle() throws Exception {
//        List<ArticleVO> articleVOS = boardService.listArticles();

//        Pagination pagination = new Pagination(1,1,10,40);
        Pagination pagination = pageMaker.pageSort(1, 1, 10, 40);
        List<ArticleVO> list = sqlSession.selectList("selectAllArticlesList", pagination);
//        List<ArticleVO> articleVOS = boardDAO.selectAllArticlesList();
//        List<ArticleVO> list = boardDAO.selectAllArticlesList();
        for (ArticleVO articleVO : list) {
            System.out.println(articleVO.getTitle());

        }
        ArticleVO articleVO = new ArticleVO();
        Map<String, String> articleMap=new HashMap<>();
//        for (int i = 70; i < 500; i++) {
//            articleMap.put("id", (i + 50 )+"");
//            articleMap.put("title", i+"");
//            articleMap.put("content", i+"");
//            boardDAO.insertNewArticle(articleMap);
//        }
//        for (int i = 70; i < 550; i++) {
//            boardDAO.deleteArticle(i);
//        }

    }
//    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


//    @Test
//    public void findBean(){
//        BoardDAO bean = ac.getBean(BoardDAOImpl.class);
//        assertThat(bean).isInstanceOf(BoardDAOImpl.class);
//    }
    

}
