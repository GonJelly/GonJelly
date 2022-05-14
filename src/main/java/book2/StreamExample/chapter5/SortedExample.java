package book2.StreamExample.chapter5;

import java.util.Arrays;
import java.util.List;

public class SortedExample {

    public static void main(String[] args) {

        List<String> sortedList = Arrays.asList("Hello", "Apple", "ever", "forever", "blue");

        sortedList.stream().sorted().forEach(System.out::println);

    }

}
