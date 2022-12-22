package com.spring.board.login.controller;

import com.spring.board.login.vo.LoginForm;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface LoginController {
    public String loginForm(@ModelAttribute LoginForm loginForm, Model model);
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                          String redirectURL,
                          HttpServletRequest request, Model model);
    public String logout(HttpServletRequest request);
}
