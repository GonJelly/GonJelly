package IOExample.chapter6;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerExample {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost",5001));

            while(true){
                System.out.println("[연결 기다림]");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[연결 수락]" + isa.getHostName());

                // 입력 스트림 만들기
                byte[] bytes = new byte[100];
                String message = null;

                InputStream is = socket.getInputStream();
                int len = is.read(bytes);
                String receive = new String(bytes, 0, len, "UTF-8");
                System.out.println("받은 메시지 : " + receive);

                // 출력 스트립 만들기
                OutputStream os = socket.getOutputStream();
                message = "Client Hello";
                os.write(message.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
