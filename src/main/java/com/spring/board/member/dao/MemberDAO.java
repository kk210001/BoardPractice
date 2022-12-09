package com.spring.board.member.dao;

import com.spring.board.member.dto.MemberDTO;
import org.springframework.dao.DataAccessException;


public interface MemberDAO {
	public void insertNewMember(MemberDTO memberDTO) throws DataAccessException;
	public MemberDTO selectMember(String memberId) throws DataAccessException;
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	public void deleteMember(String memberId) throws DataAccessException;

}
