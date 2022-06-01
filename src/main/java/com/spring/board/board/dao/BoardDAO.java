package com.spring.board.board.dao;

import com.spring.board.board.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface BoardDAO {
	public List<ArticleVO> selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;



}
