package IOExample.chapter2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputExample {

    public static void main(String[] args) throws IOException {


        File originFile = new File("C:\\test1.txt");
        File copyFile = new File("C:\\Users\\qlwms\\Downloads\\test.txt");

        FileInputStream fis = new FileInputStream(originFile);
        FileOutputStream fos = new FileOutputStream(copyFile);

        byte[] ba = new byte[100];
        int len;

        while ( (len = fis.read(ba)) != -1) {
            fos.write(ba,0, len);
        }

        fos.flush();
        fos.close();
        fis.close();

        System.out.println("복사되었습니다.");
    }
}
