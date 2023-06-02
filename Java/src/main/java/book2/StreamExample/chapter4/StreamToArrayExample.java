package book2.StreamExample.chapter4;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamToArrayExample {

    public static void main(String[] args) {

        int[] score = {90,85,75};
        String[] name = {"홍딜동", "이순신", "박준영"};

        Stream<String> stream= Arrays.stream(name);
        stream.forEach(System.out::println);

        System.out.println();

        IntStream streamToInt = Arrays.stream(score);
        streamToInt.forEach(System.out::println);
    }


}
