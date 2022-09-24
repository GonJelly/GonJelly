package IOExample.chapter3;

import java.io.*;

public class WriteExample {

    public static void main(String[] args) throws IOException {

        int len;
        char[] store = new char[100];

        Reader reader = new FileReader("C:\\test1.txt");
        Writer writer = new FileWriter("C:\\Users\\qlwms\\Downloads\\test.txt");

        while( (len = reader.read(store)) != -1) {
            writer.write(store, 0, len);
        }

        writer.flush();
        writer.close();
        reader.close();

    }
}
