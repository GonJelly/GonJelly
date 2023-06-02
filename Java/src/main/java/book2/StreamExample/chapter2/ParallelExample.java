package book2.StreamExample.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelExample {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("신용권", "이순신", "박용궐", "이성계", "이황");

        Stream<String> stream1 = list.stream();
        stream1.forEach(ParallelExample::print);

        System.out.println();

        Stream<String> stream2 = list.parallelStream();
        stream2.forEach(ParallelExample::print);

    }

    public static void print(String str) {
        System.out.println(str + " : " + Thread.currentThread());
    }

}
