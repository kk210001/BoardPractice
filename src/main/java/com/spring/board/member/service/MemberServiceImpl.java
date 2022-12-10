package com.spring.board.member.service;


import com.spring.board.member.dao.MemberDAO;
import com.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Slf4j
@Service("memberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDAO memberDAO;

    @Override
    public void addMember(MemberDTO memberDTO) throws Exception {
        memberDTO.setJoin_date(Timestamp.valueOf(LocalDateTime.now()));
        log.info("service layer add Member = {}", memberDTO);
        memberDAO.insertNewMember(memberDTO);
    }

    @Override
    public MemberDTO selectMember(String memberId) throws Exception {
        log.info("service layer search Member = {}", memberId);
        MemberDTO memberDTO = memberDAO.selectMember(memberId);
        return memberDTO;
    }

    @Override
    public void deleteMember(String memberId) throws Exception {
        log.info("service layer delete Member = {}", memberId);
        memberDAO.deleteMember(memberId);
    }
}
