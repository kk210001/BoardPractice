package com.spring.board.board.service;

import com.spring.board.board.dao.BoardDAO;
import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Transactional(propagation = Propagation.REQUIRED)
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public List<ArticleVO> listArticles(Pagination pagination) throws Exception {
        List<ArticleVO> articlesList = boardDAO.selectAllArticlesList(pagination);
        return articlesList;
    }


    @Override
    public int addNewArticle(Map articleMap) throws Exception {
        int articleNO = boardDAO.insertNewArticle(articleMap);
        articleMap.put("articleNO", articleNO);
        return articleNO;
    }


    @Override
    public Map viewArticle(int articleNO) throws Exception {
        Map articleMap = new HashMap();
        boardDAO.addViewCount(articleNO);
        ArticleVO articleVO = boardDAO.selectArticle(articleNO);
        articleMap.put("article", articleVO);
        return articleMap;
    }


    @Override
    public void modArticle(Map articleMap) throws Exception {
        boardDAO.updateArticle(articleMap);
    }

    @Override
    public void removeArticle(int articleNO) throws Exception {
        System.out.println(" service에서 삭제완료 ");
        boardDAO.deleteArticle(articleNO);
    }

//	@Override
//	public List<ArticleVO> listArticles() throws Exception {
//		return null;
//	}

    @Override
    public int getBoardAllCount() throws Exception {
        return boardDAO.getBoardAllCount();
    }


}
