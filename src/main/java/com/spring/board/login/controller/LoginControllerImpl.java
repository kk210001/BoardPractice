package com.spring.board.login.controller;

import com.spring.board.login.service.LoginService;
import com.spring.board.login.vo.LoginForm;
import com.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("loginController")
@Slf4j
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController{
    private final LoginService loginService;
    @Override
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm, Model model) {
        return "login/loginForm";
    }

    @Override
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request,Model model) {
//        model.addAttribute("command", form);
        log.info("loginForm = {}", form);
        log.info("bindingResult = {}", bindingResult);
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        MemberDTO loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("loginMember = {}", loginMember);
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);

        return "redirect:/board/listArticles.do";
    }

    @Override
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/board/listArticles.do";
    }
}
