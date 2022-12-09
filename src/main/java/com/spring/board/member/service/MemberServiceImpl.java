package com.spring.board.member.service;


import com.spring.board.member.dao.MemberDAO;
import com.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service("memberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDAO memberDAO;

    @Override
    public void addMember(MemberDTO memberDTO) throws Exception {
        memberDAO.insertNewMember(memberDTO);
        log.info("service layer add Member = {}", memberDTO);
    }

    @Override
    public MemberDTO selectMember(String memberId) throws Exception {
        MemberDTO memberDTO = memberDAO.selectMember(memberId);
        log.info("service layer search Member = {}", memberId);
        return memberDTO;
    }

    @Override
    public void deleteMember(String memberId) throws Exception {
        memberDAO.deleteMember(memberId);
        log.info("service layer delete Member = {}", memberId);
    }
}
