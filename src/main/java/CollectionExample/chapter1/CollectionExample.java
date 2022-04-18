package CollectionExample.chapter1;

import java.util.*;

public class CollectionExample {

    /** 콜렉션이 나온 배경
     *
     * 자바에서 여러개의 데이터를 저장하기 위해서는 배열을 사용하면 되지만
     * 배열을 사용하게 되면 아래와 같은 문제가 발생한다.
     *
     * 1. 불특정 다수의 객체를 저장힉에는 문제가 있음
     * 2. 배열 중간중간에 객체를 삭제하고 다른 객체를 저장하려면
     *    배열에 있는 내용을 확인하기 위한 추가적인 코드가 필요함
     * 즉, 확인도 어렵고 코드량이 늘어남
     *
     * 결론, 객체를 효율적으로 추가, 삭제, 검색할 수 있도록 하기 위해서!!
     * java.util 패키지에서 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다.
     *
     * 총칭 컬렉션 프레임워크(Collection Framework)라고 부른다.
     *
     * */

    public static void main(String[] args) {

        /* 대표 컬렉션 요소
        * List, Set, Map 인터페이스가 있다.!!
        *
        * 해당 인터페이스로 구현한 클래스는 아래왁 같다.
        * List - ArrayList, LinkedList
        * Set - HashSet, TreeSet
        * Map - HashMap, HashTable, TreeMap, Properties
        *
        * 많이 사용하는 것이 List는 ArrayList, Set은 HashSet, Map은 HashMap을 많이 사용
        *
        * LIst와 Set은 많은 공통된 점이 있기에 공통된 메서드들은 모아서 Collection 인터페이스에서 관리하고 있다.
        * Collection ---------- List ------------ ArrayList
        *               |                   |
        *               |                   ----- LinkedList
        *               |
        *               ------- Set ------------- HashSet
        *                                   |
        *                                   ----- TreeSet
        *
        * */

        // Collection 인터페이스를 이용해서 List, Set 객체 생성
        Collection<String> collectionList = new ArrayList<String>();
        Collection<String> collectionSet = new HashSet<String>();

        // List, Set 인터페이스를 이용해서 객체 생성
        // List<String> arrayList = new List<String>(); // 잘못된 예시
        List<String> arrayList = new ArrayList<String>();
        Set<Integer> hashSet = new HashSet<>();

        // Map은 Key 와 value로 구성되어 있기 때문에 제네릭을 사용할 때는 타입 파라미터가 2개 들어가줘야한다.
        Map  sampleMap = new HashMap();
        Map<String, Object> hashMap = new HashMap<>();

        /** 주의사항 #####
         *
         *  각 컬렉션 요소의 특징
         *  [List]
         *  1. 순서를 유지하고 저장한다. 즉, index로 객체를 관리한다.
         *  2. 중복이 가능하다.
         *
         *  [Set]
         *  1. 순서를 유지하지 않고 저장한다.
         *  2. 중복이 불가능하다.
         *
         *  [Map]
         *  1. Key 와 value 쌍으로 저장관리
         *  키는 중복 저장 안됨!!
         *
         * */
    }

}
