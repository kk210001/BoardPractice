package com.spring.board.member.controller;


import com.spring.board.member.dto.MemberDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

public interface MemberController {

    public String getAddForm(@ModelAttribute MemberDTO memberDTO) throws Exception;
    public String addForm(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) throws  Exception;
}