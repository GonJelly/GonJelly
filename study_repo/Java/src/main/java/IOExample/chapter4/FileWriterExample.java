package IOExample.chapter4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

    public static void main(String[] args) throws IOException {

        char[] stores = new char[100];
        int len;

        FileReader fr = new FileReader("C:\\test1.txt");

        File file = new File("C:\\Users\\qlwms\\Downloads\\FileWriter.txt");
        FileWriter fw = new FileWriter(file);

        while( (len = fr.read(stores)) != -1) {
            fw.write(stores, 0, len);
        }

        fw.flush();
        fw.close();
        fr.close();

    }
}
