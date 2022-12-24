package com.spring.board.login.service;

import com.spring.board.member.dao.MemberDAO;
import com.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("LoginService")
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberDAO memberDAO;

    @Override
    public MemberDTO login(String loginId, String password)  {
              MemberDTO member = memberDAO.selectMember(loginId);
        if (member.getPassword().equals(password)){
           return member;
        }else {
            return null;
        }
//        return Optional.of(memberDAO.selectMember(loginId))
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);
    }
}
