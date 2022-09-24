package IOExample.chapter2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputExample {

    public static void main(String[] args) throws IOException {

        // 경로에 있는 파일을 가져온다.
        File file = new File("C:\\test1.txt");
        FileInputStream files = new FileInputStream(file);

        // 받아온 파일을 저장할 공간을 선언
        byte[] stores = new byte[8];
        int storeLength;

        while ( (storeLength = files.read(stores)) != -1) {

            for(byte store : stores) {
                // 문자로 변환
                char byteStreamChar = (char) store;
                System.out.print(byteStreamChar);
            }

        }

        files.close(); // FileInputStream을 닫음

    }
}
