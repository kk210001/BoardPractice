package com.spring.board.board.dao;

import com.spring.board.board.vo.ArticleVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession sqlSession;




	@Autowired
	public BoardDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ArticleVO> selectAllArticlesList() throws DataAccessException {

		List<ArticleVO> articlesList = sqlSession.selectList("selectAllArticlesList");
		System.out.println("성공");
		return articlesList;
	}

	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("insertNewArticle",articleMap);
		return articleNO;
	}


	@Override
	public void addViewCount(int articleNO) throws DataAccessException {
		sqlSession.selectOne("addViewCount", articleNO);
	}

	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("selectArticle", articleNO);
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("updateArticle", articleMap);
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("deleteArticle", articleNO);
		System.out.println(" dao에서 삭제완료 " );
		
	}
	

	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("selectNewArticleNO");
	}
	


}
