package com.ssafy;

import com.ssafy.member.model.dto.Member;

public class MainController {

    private static Member session;

    public static void main(String[] args) {

        System.out.println("=========== 구해줘 홈즈 ==============");
        System.out.println("=========== 구해줘 홈즈 ==============");
        System.out.println("=========== 구해줘 홈즈 ==============");
        System.out.println("=========== 구해줘 홈즈 ==============");
        System.out.println("=========== 구해줘 홈즈 ==============");
        System.out.println("=========== 구해줘 홈즈 ==============");

    }

    private static void content() {

        System.out.println("======== service를 시작합니다. ==========");
        System.out.println("========  ==========");
        if( session != null ) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 로그인");
            System.out.println("4. 로그인");
        } else {

        }

    }
}
