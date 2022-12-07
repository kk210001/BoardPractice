package com.spring.board.member.service;

import com.spring.board.member.vo.MemberVO;

public interface MemberService {
    public void addMember(MemberVO memberVO) throws Exception;
    public MemberVO selectMember(String memberId) throws Exception;
    public void deleteMember(String memberId) throws Exception;
}
