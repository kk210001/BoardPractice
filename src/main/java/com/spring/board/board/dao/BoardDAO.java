package com.spring.board.board.dao;

import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.paging.Pagination;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface BoardDAO {
//	public List<ArticleDTO> selectAllArticlesList() throws DataAccessException;
	public List<ArticleDTO> selectAllArticlesList(Pagination pagination) throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public ArticleDTO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public void addViewCount(int articleNO) throws DataAccessException;
	public int getBoardAllCount()throws Exception;

}
