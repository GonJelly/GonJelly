package com.ssafy.member.controller;

import com.ssafy.member.model.Member;
import com.ssafy.member.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    // 회원가입 페이지 이동
    @RequestMapping(value = "/registpage", method = RequestMethod.GET)
    public String registpage() {
        return "member/regist";
    }
    //로그인페이지 이동
    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String loginpage() {
        return "member/login";
    }

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage() {
        return "member/mypage";
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

    // 회원가입하기
    @RequestMapping(value ="/regist", method = RequestMethod.POST)
    public String regist(Member member, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        logger.debug("MemberController regist() user : {}",member);

        try {
            member.setUserName(firstName + lastName);
            memberService.addMember(member);
            return "redirect:/user/loginpage";
        } catch( Exception e ) {
            e.printStackTrace();
            return "error/error";
        }
    }

    @RequestMapping(value = "/searchPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> searchPwd(@RequestBody Member member) {
        logger.debug("MemberController searchPwd() Params {}", member);
        Map<String,Object> result = new HashMap<>();
        try {
            String password = memberService.searchPwd(member);
            if( password != null && !password.equals("")) {
                Map<String,Object> updateMap = new HashMap<>();
                // 새로운 비밀번호 12자리 생성
                String newPass = randomPwd();
                // 새로운 비밀번호로 변경
                updateMap.put("userId",member.getUserId());
                updateMap.put("userName",member.getUserName());
                updateMap.put("newPass",newPass);

                memberService.updatePass(updateMap);

                result.put("newPass",newPass);
                return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
            } else {
                result.put("message","정보가 정확하지 않습니다.");
                return new ResponseEntity<Map<String,Object>>(result,HttpStatus.NO_CONTENT);
            }
        } catch( Exception e ) {
            e.printStackTrace();
            return new ResponseEntity<Map<String,Object>>(result,HttpStatus.EXPECTATION_FAILED);
        }
    }

    private String randomPwd() {

        int leftLimit = 48;
        int rightLimit = 122;
        int totalLimit = 12;
        Random random = new Random();
        String generator = random.ints(leftLimit, rightLimit - 1)
                                 .filter(num -> (num <= 57 || num >= 65) && ( num <= 90 || num >= 97))
                                 .limit(totalLimit)
                                 .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                 .toString();

        return generator;
    }

}
