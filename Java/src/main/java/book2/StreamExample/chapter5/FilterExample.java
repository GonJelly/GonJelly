package book2.StreamExample.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilterExample {

    /** 중간처리 메소드 예제
     *
     *  1. distinct() : 중복객체 제거
     *  2. filter() : 조건 필터링
     *
     * */
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        
        list.add("홍길동");
        list.add("신영길");
        list.add("이순신");
        list.add("홍길동"); // 중복 객체
        list.add("김두한");
        list.add("이순신"); // 중복 객체

        // 스트림 중복객체 제거 메소드 :  distinct()
        list.stream().distinct().forEach(System.out::println);

        System.out.println();

        // 스트림 조건에 맞는 필터링 : filter()
        list.stream()
                .distinct() // 중간처리 시작
                .filter( n -> n.startsWith("신") || n.endsWith("동"))
                .forEach(System.out::println); // 최종 처리
        
    }
}
