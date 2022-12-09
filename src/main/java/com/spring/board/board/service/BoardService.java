package com.spring.board.board.service;

import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.paging.Pagination;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public List<ArticleDTO> listArticles(Pagination pagination) throws Exception;
	public Pagination paging(int page, int listSize, String type, String keyword) throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public Map viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
}
