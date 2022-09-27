package com.ssafy.main;

import com.ssafy.main.model.Member;
import com.ssafy.main.service.MemberService;
import com.ssafy.main.service.MemberServiceImpl;
import com.ssafy.util.CityLoader;

import java.util.List;
import java.util.Scanner;

public class MainController {
    private static Scanner in;
    private static MemberService service;
    private static Member session;
    public static void main(String[] args) {
        CityLoader loader = CityLoader.getInstance();
        loader.load();
        System.out.println("========== 인스타 시작 =============");
        in = new Scanner(System.in);
        service = MemberServiceImpl.getInstance();
        try {
            String command = "";
            while ( !command.equals("exit") ) {
                command = contents(session);
                switch ( command ) {
                    case "login":
                        session = login();
                        break;
                    case "logout":
                        session = null;
                        break;
                    case "myInfo":
                        myInfo(session);
                        break;
                    case "register":
                        register();
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("시스템 오류가 발생했습니다.!!");
            System.exit(0);
        }

    }

    private static String contents(Member user) {

        System.out.println("============== 번호를 입력해주세요. ================");
        System.out.println("====== ( 해당 번호 외를 입력하시면 종료됩니다. ) =======");

        int input = 0;
        if( user != null ) {

            System.out.print("1. 로그아웃 \n");
            System.out.print("2. 마이페이지 \n");
            System.out.print("3. 회원조회 \n");


            input = in.nextInt();

            switch ( input ) {
                case 1:
                    return "logout";
                case 2:
                    return "myInfo";
                case 3:
                    return "memberSelect";
                default:
                    System.out.println("시스템을 종료합니다.!!");
                    return "exit";
            }

        } else {
            System.out.print("1. 로그인 \n");
            System.out.print("2. 회원가입 \n");

            input = in.nextInt();

            switch ( input ) {
                case 1:
                    return "login";
                case 2:
                    return "register";
                default:
                    return "exit";
            }
        }
    }

    private static Member login() throws Exception{

        System.out.print("로그인 ID : ");
        String userid = in.next();
        System.out.print("로그인 PW : ");
        String userPw = in.next();

        Member tmp = service.getUser(userid,userPw);
        tmp.setFollowing(service.getFollowing(userid));
        return tmp;
    }

    private static void myInfo(Member user) throws Exception{

        System.out.println("============ 내 정보 조회 ============");
        System.out.println("회원 ID : " + user.getUserid());
        System.out.println("회원 PW : " + user.getUserPw());
        System.out.println("회원 NAME : " + user.getName());
        System.out.println("회원 EMAIL : " + user.getEmail());

        System.out.println("============= 내 팔로윙 =============");
        for(String name : user.getFollowing()) {
            System.out.println("팔로윙한 사람 : " + name);
        }

        System.out.println("============= 내 팔로워 =============");
        List<String> followers = service.getFollower(user);
        followers.forEach(follower -> {
            System.out.println("팔로워 : " + follower);
        });

        System.out.println("============= 번호를 입력해주세요. =============");
        System.out.println("회원정보 수정 : 1");
        System.out.println("회원정보 탈최 : 2");
        System.out.println("프로필 나가기 : 아무키나.. ");
        int button = in.nextInt();

        switch ( button ) {
            case 1:
                updateMember(user);
                break;
            case 2:
                deletemember(user);
                break;
            default:
                System.out.println("============ 프로필에서 나갔습니다. =============");
                return;
        }
    }

    private static void updateMember(Member user) throws Exception {

        System.out.println("========= 수정할 정보를 입력해주세요. ==========");
        String changePW = "";
        while ( true ) {
            System.out.print("회원 PW : ");
            changePW = in.next();
            System.out.print("회원 PW 확인 : ");
            String confirmPW = in.next();

            if ( changePW.equals(confirmPW) ) break;

            System.out.println("========= 비밀번호가 맞지않습니다. ==========");

            System.out.println("========= 수정을 종료학려면 [1]를 입력해주세요. ==========");
            int exit = in.nextInt();
            if( exit == 1) return;
        }

        System.out.print("회원 이름 : ");
        String changeName = in.next();

        System.out.print("회원 이메일 : ");
        String changeEmail = in.next();

        Member change = new Member();

        change.setUserid(user.getUserid());
        change.setUserPw(changePW);
        change.setName(changeName);
        change.setEmail(changeEmail);

        int cnt = service.modifiedMember(change);
        System.out.println(cnt);
        if( cnt > 0 ) {
            System.out.println("=========== 정상적으로 수정되었습니다. ==========");
            session = change;
        } else {
            System.out.println("========== 회원정보가 수정되지 않았습니다. ==========");
        }
    }

    private static void deletemember(Member user) throws Exception {
        int result = service.deleteMember(user);
        if( result > 0 ) {
            System.out.println("========== [회원탈퇴 성공] ============");
            session = null;
        } else {
            System.out.println("========== [회원탈퇴 실패] ============");
        }
    }
    private static void register() throws Exception {

        System.out.println("============== 회원가입 ==============");
        String id = "";
        while ( true ) {
            System.out.print("회원 ID : ");
            id = in.next();
            if( service.idCheck(id) == null ) break;

            System.out.println("아이디가 중복되었습니다.( 계속입력 => 1 종료 => 2 )");
            int isvail = in.nextInt();

            if( isvail == 2 ) return;
        }
        System.out.print("회원 PW : ");
        String pw = in.next();
        System.out.print("회원 name : ");
        String name = in.next();
        System.out.print("회원 email : ");
        String email = in.next();

        Member user = new Member();

        user.setUserid(id);
        user.setUserPw(pw);
        user.setName(name);
        user.setEmail(email);

        int result = service.addMember(user);
        if( result != 0) {
            System.out.println("============= 회원가입 성공 =============");
        } else {
            System.out.println("============= 회원가입 실패 =============");
        }
    }

}
