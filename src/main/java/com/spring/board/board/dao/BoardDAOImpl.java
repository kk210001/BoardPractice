package com.spring.board.board.dao;

import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.paging.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Mapper
@Repository("boardDAO")
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession sqlSession;

	@Override
	public List<ArticleDTO> selectAllArticlesList(Pagination pagination) throws DataAccessException {
		List<ArticleDTO> articlesList = sqlSession.selectList("selectAllArticlesList", pagination);
		return articlesList;
	}

	@Override
	public void insertNewArticle(Map articleMap) throws DataAccessException {
		log.info("insert article info : {}", articleMap);
		sqlSession.insert("insertNewArticle",articleMap);
	}


	@Override
	public void addViewCount(int articleNO) throws DataAccessException {
		sqlSession.selectOne("addViewCount", articleNO);
	}

    @Override
    public int getBoardAllCount() throws Exception {
		log.info("게시글 갯수 호출");
        return sqlSession.selectOne("tableAllCount");
    }


	@Override
	public ArticleDTO selectArticle(int articleNO) throws DataAccessException {
		log.info("게시글 {}번 호출 완료",articleNO);
		return sqlSession.selectOne("selectArticle", articleNO);
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("updateArticle", articleMap);
		log.info("게시글 {}번 호출 완료",articleMap.get("articleNO"));
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("deleteArticle", articleNO);
		log.info("게시글 {}번 삭제 완료",articleNO);
		
	}


	@Override
	public int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("selectNewArticleNO");
	}
	


}
