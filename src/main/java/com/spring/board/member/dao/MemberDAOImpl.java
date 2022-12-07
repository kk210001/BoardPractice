package com.spring.board.member.dao;

import com.spring.board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Slf4j
@Mapper
@Repository("memberDAO")
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO{

    private final SqlSession sqlSession;

    @Override
    public void insertNewMember(MemberVO memberVO) throws DataAccessException {
        System.out.println("memberVO = " + memberVO);
        sqlSession.insert("addMember",memberVO);
        System.out.println(" sqlsession 확인");
        log.info("add member = {}",memberVO);
    }

    @Override
    public MemberVO selectMember(String memberId) throws DataAccessException {
        MemberVO selectMember = sqlSession.selectOne("selectMember", memberId);
        log.info("search member = {}", selectMember);
        return selectMember;
    }

    @Override
    public void updateMember(MemberVO memberVO) throws DataAccessException {
        sqlSession.update("updateMember", memberVO);
        log.info("update member = {}",memberVO);
    }

    @Override
    public void deleteMember(String memberId) throws DataAccessException {
        sqlSession.delete("deleteMember", memberId);
        log.info("delete memberId=  {}",memberId);
    }
}
