package com.ssafy.member.controller;

import com.ssafy.member.model.Member;
import com.ssafy.member.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/user")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    //로그인페이지 이동
    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String loginpage() {
        return "member/login";
    }
    // 로그인하기
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Member member, HttpSession session, @RequestParam(value = "ckId",required = false) String ckId, HttpServletResponse response) {

        logger.debug("MemberController login() member : {}", member);
        logger.debug("MemberController login() ckId : {}", ckId);

        try {
            // 로그인 정보조회
            Member user = memberService.memberInfo(member);
            if( user != null ) {
                // 세션정보 저장
                session.setAttribute("userInfo",user);
                // 쿠키 생성하기
                Cookie cookie = new Cookie("saveId",user.getUserId());
                cookie.setPath("/");
                // 체크되어 있으면 쿠키 생명주기 설정
                if("remember-me".equals(ckId)) {
                    cookie.setMaxAge(60*60*24*365*50);
                } else {
                    cookie.setMaxAge(0);
                }
                response.addCookie(cookie);
                return "index";
            } else {
                return "member/login";
            }
        } catch( Exception e ) {
            e.printStackTrace();
            return "error/error";
        }

    }

    // 입력한 아이디가 있는지 확인하기
    @RequestMapping(value = "/{userId}")
    @ResponseBody
    public ResponseEntity<?> idCheck(@PathVariable String userId) {
        logger.debug("MemberController idCheck() userId : {}", userId);
        try {
            int cnt = memberService.idCheck(userId);
            return new ResponseEntity<Integer> (cnt,HttpStatus.OK);
        } catch( Exception e ) {
            e.printStackTrace();
            return new ResponseEntity<String> ("오루발생[idCheck] " + e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    // 로그아웃 하기
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Enumeration<String> enumer =  session.getAttributeNames();
        while ( enumer.hasMoreElements() ) {
            String key = enumer.nextElement();
            if( key.equals("userInfo") ) {
                session.removeAttribute(key);
            }
        }
        session.invalidate();
        return "index";
    }

}
