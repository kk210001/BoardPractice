package com.spring.board.board.service;

import com.spring.board.board.dao.BoardDAO;
import com.spring.board.board.vo.ArticleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



//@Transactional(propagation = Propagation.REQUIRED)
@Service("boardService")
public class BoardServiceImpl  implements BoardService{

	private final BoardDAO boardDAO;
	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public List<ArticleVO> listArticles() throws Exception {

		List<ArticleVO> articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}

	

	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		return articleNO;
	}



	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		boardDAO.addViewCount(articleNO);
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		System.out.println("viewCount = " + articleVO.getViewCount());
		articleMap.put("article", articleVO);
		return articleMap;
	}


	//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		return boardDAO.insertNewArticle(articleMap);
//	}

//	@Override
//	public ArticleVO viewArticle(int articleNO) throws Exception {
//		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
//		return articleVO;
//	}
	
	
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
	

	
}
