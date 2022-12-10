package com.spring.board;

import com.spring.board.login.service.LoginService;
import com.spring.board.login.vo.LoginForm;
import com.spring.board.member.dto.MemberDTO;
import com.spring.board.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
public class LoginTest {


    private final LoginService loginService;

    @Autowired
    LoginTest(LoginService loginService){
        this.loginService = loginService;
    }
    @Test
    public void Login() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setLoginId("testId");
        loginForm.setPassword("1234");
        MemberDTO login = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        Assertions.assertThat(login.getMemberId()).isEqualTo(loginForm.getLoginId());
        Assertions.assertThat(login.getPassword()).isEqualTo(loginForm.getPassword());

    }
}