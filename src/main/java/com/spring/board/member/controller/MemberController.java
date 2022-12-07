package com.spring.board.member.controller;


import com.spring.board.member.vo.MemberVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

public interface MemberController {

    public String addForm(@ModelAttribute MemberVO memberVO) throws Exception;
    public String addForm(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws  Exception;
}