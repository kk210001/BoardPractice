package com.spring.board.login.service;


import com.spring.board.member.dto.MemberDTO;

public interface LoginService {

    public MemberDTO login(String loginId, String password);
}
