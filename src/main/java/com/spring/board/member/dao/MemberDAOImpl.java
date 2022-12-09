package com.spring.board.member.dao;

import com.spring.board.member.dto.MemberDTO;
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
    public void insertNewMember(MemberDTO memberDTO) throws DataAccessException {
        System.out.println("memberDTO = " + memberDTO);
        sqlSession.insert("addMember", memberDTO);
        System.out.println(" sqlsession 확인");
        log.info("add member = {}", memberDTO);
    }

    @Override
    public MemberDTO selectMember(String memberId) throws DataAccessException {
        MemberDTO selectMember = sqlSession.selectOne("selectMember", memberId);
        log.info("search member = {}", selectMember);
        return selectMember;
    }

    @Override
    public void updateMember(MemberDTO memberDTO) throws DataAccessException {
        sqlSession.update("updateMember", memberDTO);
        log.info("update member = {}", memberDTO);
    }

    @Override
    public void deleteMember(String memberId) throws DataAccessException {
        sqlSession.delete("deleteMember", memberId);
        log.info("delete memberId=  {}",memberId);
    }
}
