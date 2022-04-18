package CollectionExample.chapter2;

import java.util.ArrayList;
import java.util.List;

public class CollectionList {

    /** List의 각 메소드에 익숙해지기
     *
     * 객체추가
     * 객체검색
     * 객체삭제
     *
     * */
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("홍길동");
        list.add(1, "싱용권");
        String str = list.get(1);
        list.remove(0);
        list.remove("신용권");

        // List의 저자오딘 객체 수 만큼 루핑
        for(int i = 0; i < list.size(); i++){
            String loop = list.get(i);
            System.out.println(loop);
        }

        System.out.println("================");

        // 향상된 for문을 이용하여 콜렉션 루핑
        for(String name : list) {
            System.out.println(name);
        }

        System.out.println("================");

        // 스트림을 이용해서 list에 객체를 출력
        list.stream().forEach(System.out::println);


    }
}
