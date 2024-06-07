package IOExample.chapter5;

import java.io.*;

/** 문자변환 보조스트림
 * */
public class InputStreamReaderExample {

    public static void main(String[] args) throws IOException {

        byte[] stores = new byte[20];
        char[] storesChar = new char[20];
        int len;

        File file = new File("C:\\test1.txt");
        FileInputStream fis = new FileInputStream(file);

        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);

        // 문자변환 보조스트림을 사용하지 않음
        while( (len = fis.read(stores)) != -1) {
            for(byte store : stores) {
                char st =  (char) store ;
                System.out.print(st);
            }
        }

        System.out.println("\n\n");

        // 문자변환 보조스트림을 사용함
        while( (len = isr.read(storesChar)) != -1 ) {
            String st = new String(storesChar, 0, len);
            System.out.print(st);
        }

        fis.close();
        isr.close();
    }
}
