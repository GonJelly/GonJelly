package IOExample.chapter6;

import java.net.InetAddress;

public class SocketExample {

    public static void main(String[] args) throws Exception{

        InetAddress local = InetAddress.getLocalHost();
        System.out.println("내 컴퓨터 IP주소: " + local.getHostAddress());

        InetAddress[] naverArr = InetAddress.getAllByName("www.naver.com");
        for(InetAddress naver : naverArr){
            System.out.println(naver);
        }
    }
}
