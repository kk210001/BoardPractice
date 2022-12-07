package com.spring.board.member.dao;

import com.spring.board.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;


public interface MemberDAO {
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMember(String memberId) throws DataAccessException;
	public void updateMember(MemberVO memberVO) throws DataAccessException;
	public void deleteMember(String memberId) throws DataAccessException;

}
