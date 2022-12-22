package com.spring.board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("사용자 확인 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("loginMember") == null) {
            log.info("로그인하지 않은 사용자");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }


        return true;
    }
}
