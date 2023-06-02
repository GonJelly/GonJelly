package IOExample.chapter1;

import java.io.*;

public class OutputStreamExample {

    public static void main(String[] args) throws IOException {

        // 내용 저장공간
        byte[] stores = new byte[100];
        int len;

        // 복사할 파일 불러오기
        InputStream is = new FileInputStream("C:\\test1.txt");

        // 저장할 공간 지정
        File file = new File("C:\\Users\\qlwms\\Downloads\\OutputExample.txt");
        OutputStream os = new FileOutputStream(file);

        while( (len = is.read(stores)) != -1) {

            for(byte store : stores) {
                char character = (char) store;
                System.out.print(character);
            }

            os.write(stores,0,len);
        }

        // 종료
        os.flush();
        os.close();
        is.close();

    }
}
