package com.spring.board;


import com.spring.board.member.dao.MemberDAO;
import com.spring.board.member.service.MemberService;
import com.spring.board.member.service.MemberServiceImpl;
import com.spring.board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("testId6");
        memberVO.setPassword("1234");
        memberVO.setNickname("testName");
        memberVO.setJoin_date(Timestamp.valueOf(LocalDateTime.now()));

        memberService.addMember(memberVO);
        MemberVO result = memberService.selectMember(memberVO.getMemberId());
        Assertions.assertThat(result).isEqualTo(memberVO);

    }
}
