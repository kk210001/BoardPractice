package com.spring.board.board.dao;

import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Mapper
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession sqlSession;



	@Autowired
	public BoardDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

//	@Override
//	public List<ArticleVO> selectAllArticlesList() throws DataAccessException {
//
//		List<ArticleVO> articlesList = sqlSession.selectList("selectAllArticlesList");
//		System.out.println("성공");
//		return articlesList;
//	}
	@Override
	public List<ArticleVO> selectAllArticlesList(Pagination pagination) throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("selectAllArticlesList", pagination);
		System.out.println("게시글 리스트 호출 완료");
		return articlesList;
	}

	@Override
	public List<ArticleVO> selectAjaxArticlesList(ArticleVO articleVO) throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("selectAjaxArticlesList", articleVO);
		log.info("ajax 요청 dao 에서 성공 완료");
		return articlesList;
	}

	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		System.out.println(" 삽입 완료");
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
    public int getBoardAllCount() throws Exception {
		System.out.printf("게시글 리스트 호출 완료");
        return sqlSession.selectOne("tableAllCount");
    }


	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		System.out.println("게시글 호출 완료 " );
		return sqlSession.selectOne("selectArticle", articleNO);
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("updateArticle", articleMap);
		System.out.println(" 수정 완료 " );
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("deleteArticle", articleNO);
		System.out.println(" 삭제 완료 " );
		
	}
	

	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("selectNewArticleNO");
	}
	


}
