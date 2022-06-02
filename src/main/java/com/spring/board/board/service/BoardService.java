package com.spring.board.board.service;

import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.Pagination;

import java.util.List;
import java.util.Map;

public interface BoardService {

//	public List<ArticleVO> listArticles() throws Exception;
	public List<ArticleVO> listArticles(Pagination pagination) throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	//public ArticleVO viewArticle(int articleNO) throws Exception;
	public Map viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
	public int getBoardAllCount() throws  Exception;
}
