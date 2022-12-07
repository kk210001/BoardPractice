package com.spring.board.member.service;


import com.spring.board.member.dao.MemberDAO;
import com.spring.board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service("memberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDAO memberDAO;

    @Override
    public void addMember(MemberVO memberVO) throws Exception {
        memberDAO.insertNewMember(memberVO);
        log.info("service layer add Member = {}", memberVO);
    }

    @Override
    public MemberVO selectMember(String memberId) throws Exception {
        MemberVO memberVO = memberDAO.selectMember(memberId);
        log.info("service layer search Member = {}", memberId);
        return memberVO;
    }

    @Override
    public void deleteMember(String memberId) throws Exception {
        memberDAO.deleteMember(memberId);
        log.info("service layer delete Member = {}", memberId);
    }
}
