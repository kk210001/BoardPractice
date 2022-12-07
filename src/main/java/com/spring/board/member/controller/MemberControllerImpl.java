package com.spring.board.member.controller;

import com.spring.board.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberControllerImpl implements MemberController{

    @Override
    @GetMapping("/add")
    public String addForm(MemberVO memberVO) throws Exception {
        return "members/addMemberForm";
    }

    @Override
    @PostMapping("/add")
    public String addForm(MemberVO memberVO, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }
        return null;
    }

}
