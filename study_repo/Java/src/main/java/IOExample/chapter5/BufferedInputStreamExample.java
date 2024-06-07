package IOExample.chapter5;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BufferedInputStreamExample {

    public static void main(String[] args) throws IOException {

        byte[] stores = new byte[100];
        long result;
        int len;

        File file = new File("C:\\test1.txt");
        InputStream is_0 = new FileInputStream(file);

        InputStream is_1 = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is_1);

        long startTime, endTime;

        // 성능향상 보조스트림을 사용하지않을 경우
        startTime = System.nanoTime();
        while( (len = is_0.read(stores)) != -1) {

            for(byte store : stores) {
                char st = (char) store;
                System.out.print(st);
            }
        }
        System.out.println();
        endTime = System.nanoTime();
        System.out.println( endTime - startTime + "ns");

        System.out.println("\n\n");

        // 성능향상 보조스트림을 사용할 경우
        startTime = System.nanoTime();
        while( (len = bis.read(stores)) != -1) {
            for(byte store : stores) {
                char st = (char) store;
                System.out.print(st);
            }
        }

        System.out.println();
        endTime = System.nanoTime();
        System.out.print( endTime - startTime + "ns");

        is_0.close();
        bis.close();

    }
}
