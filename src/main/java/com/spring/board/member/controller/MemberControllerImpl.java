package com.spring.board.member.controller;

import com.spring.board.member.dto.MemberDTO;
import com.spring.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberControllerImpl implements MemberController{
    private final MemberService memberService;

    @Override
    @GetMapping("/add")
    public String getAddForm(@ModelAttribute MemberDTO memberDTO) throws Exception {
        return "members/addMemberForm";
    }

    @Override
    @PostMapping("/add")
    public String addForm(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) throws Exception {
        log.info("member = {}", memberDTO);
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }
        memberService.addMember(memberDTO);
        return "redirect:/board/listArticles.do";
    }

}
