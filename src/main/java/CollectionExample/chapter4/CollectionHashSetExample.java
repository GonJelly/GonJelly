package CollectionExample.chapter4;

import java.util.HashSet;
import java.util.Set;

public class CollectionHashSetExample {

    public static void main(String[] args) {

        Set<Member> hashSet = new HashSet<>();

        hashSet.add(new Member("홍길동", 30));
        hashSet.add(new Member("홍길동", 30));

        /* 위에 Set에 저장한 객체는 분명히 다른 객체지만
        * Member클래스에서 equals() 와 hashCode()를 재정의(Override)하여
        * 이름과 나이로 hash코드값을 생성하도록 하여서 같은 hash코드값이 나오며
        * equals()에서는 이름과 나이가 같으면 true를 리턴하도록 하였기 때문에
        *
        * 다른 객체라고 하더락도 이름과 나이가 같이 때문에 같은 객체로 인식하게 된다.
        * */

        System.out.println("hashSet의 저장된 객체 수 : " + hashSet.size()); // 1

    }

}
