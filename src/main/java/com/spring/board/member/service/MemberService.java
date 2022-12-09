package com.spring.board.member.service;

import com.spring.board.member.dto.MemberDTO;

public interface MemberService {
    public void addMember(MemberDTO memberDTO) throws Exception;
    public MemberDTO selectMember(String memberId) throws Exception;
    public void deleteMember(String memberId) throws Exception;
}
