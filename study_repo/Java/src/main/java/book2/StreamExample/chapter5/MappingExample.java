package book2.StreamExample.chapter5;

import book2.StreamExample.chapter4.Student;

import java.util.Arrays;
import java.util.List;

public class MappingExample {

    public static void main(String[] args) {

        /** flatMapXXX() 맵핑 메소드 * */
        List<String> list = Arrays.asList("java8 Lambda", "stream Mapping");

        list.stream()
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .forEach(s -> System.out.println(s));

        System.out.println();

        // 숫자를 분리해서 stream()으로 출력하기
        List<String> listInt = Arrays.asList("10,20,30,40,50,60");

        listInt.stream()
                .flatMapToInt(data -> {
                    String[] str = data.split(",");
                    int[] num = new int[str.length];

                    for(int i = 0; i < num.length; i++){
                        num[i] = Integer.parseInt(str[i]);
                    }

                    return Arrays.stream(num);
                })
                .forEach( n -> System.out.println(n));

        System.out.println();

        /** mapXXX() 맵핑 메소드 */
        List<Student> list1 = Arrays.asList(
                new Student("홍딜동", 90),
                new Student("이순신", 88),
                new Student("강호동", 92)
                );

        list1.stream()
                .map(Student::getScore)
                .forEach(s -> System.out.println(s));

        System.out.println();
    }
}
