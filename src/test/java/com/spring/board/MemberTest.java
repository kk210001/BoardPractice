package com.spring.board;


import com.spring.board.member.service.MemberService;
import com.spring.board.member.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
public class MemberTest {


    private final MemberService memberService;

    @Autowired
    MemberTest(MemberService memberService){
        this.memberService = memberService;
    }
    @Test
    public void addMemberTest() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("testId7");
        memberDTO.setPassword("1234");
        memberDTO.setNickname("testName");
        memberDTO.setJoin_date(Timestamp.valueOf(LocalDateTime.now()));

        memberService.addMember(memberDTO);
        MemberDTO result = memberService.selectMember(memberDTO.getMemberId());
        Assertions.assertThat(result).isEqualTo(memberDTO);

    }
}
