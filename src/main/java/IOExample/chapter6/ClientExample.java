package IOExample.chapter6;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ClientExample {

    public static void main(String[] args) {

        Socket socket1 = null;

        try {
            socket1 = new Socket();
//            소켓 생성과 동시에 연결요청 (없음)
//            socket1 = new Socket( new InetSocketAddress("localhost",5001) );

            System.out.println("[연결 요청]");
            socket1.connect( new InetSocketAddress("localhost", 5001));
            System.out.println("[연결 성공]");

            byte[] bytes = null;
            String message = null;

            // 출력 스트림 만들기
            OutputStream os = socket1.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            message = "server Hello";
            bos.write(message.getBytes("UTF-8"));
            bos.flush();

            // 입력 스트립 만들기
            InputStream is = socket1.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bytes = new byte[100];
            int len = bis.read(bytes);
            String receive = new String(bytes, 0, len,"UTF-8");
            System.out.println("데이터 받음 : " + receive + "\n\n");
            is.close();
            bos.close();


        } catch (UnknownHostException e1) {
            // IP 표기 방법이 잘못 되었을 경우
        } catch (IOException e2) {
            // 포트의 서버에 연결할 수 없을 경우
        }

        if(socket1.isClosed()) {
            try {
                socket1.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
