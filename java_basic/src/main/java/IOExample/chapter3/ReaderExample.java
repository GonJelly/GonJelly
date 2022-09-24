package IOExample.chapter3;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderExample {

    public static void main(String[] args) throws IOException {

        // 파일 불러오기
        Reader reader = new FileReader("C:\\test1.txt");

        // 불러온 파일 내용 저장하기
        char[] ch = new char[100];
        int len;

        int count = 0;
        // 파일
        while( (len = reader.read(ch)) != -1) {

            String st = new String(ch,0,len);
            System.out.print(st);

            count++;
        }

        System.out.println();
        System.out.print(count);

        reader.close();

    }
}
