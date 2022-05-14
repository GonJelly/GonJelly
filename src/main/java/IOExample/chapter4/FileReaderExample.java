package IOExample.chapter4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {

    public static void main(String[] args) throws IOException {

        char[] stores = new char[100];
        int len;

        File file = new File("C:\\test1.txt");
        FileReader reader = new FileReader(file);

        while((len = reader.read(stores)) != -1) {
            String store = new String(stores, 0, len);
            System.out.print(store);
        }

        reader.close();

    }
}
