package GenericExample.chapter1;

/** 제네릭 기본지식
 * 제네릭은 람다식, 컬렉션의 Set, List, Map,스트림, NIO에서 많이 사용된다.
 *
 * 제네릭이 있어서 해결되는 문제
 * 1. 컴파일시 잘좃된 타입지정으로 발생할 수 있는 오류를 방지할 수 있다.
 * 2. 강제 타입 변환(Casting)을 할 필요성이 해결
 *
 * 제네릭을 표현 방식은 클래스 or 인터페이스 설계시 이름 뒤에 '<>'부호를 붙여주면된다.
 * <>부호 안에는 타입 파라미터를 넣어주면되고
 * 제네릭으로 설계된 클래스 or 인터페이스를 객체화 할 때 <>부호 안에 타입을 지정해주면
 * 지정된 타입만 받도록 설계된다.
 *
 *  방법1
 *  box1객체를 제네릭을 사용하지않고 생성하였습니다.
 *  box1에 저장될 때는 문자열을 저장하였지만
 *  다시 객체에서 받아오기 위해서는 강제 타입 변환(Casting)을 해야합니다.
 *
 * 즉, 최상위 조상 클래스인 Object클래스를 이용해서 모든 객체를 저장할 수는 있지만
 * 받아오기 위해서는 타입을 변환해야하는 문제가 발생한다는 점
 *
 * 위 문제를 제네릭이 해결하였습니다.
 *
 * 방법2
 * 제네릭으로 클래스를 설계하고 객체화 할 때 제네릭 안에 타입 파리미터를 String으로 지정하므로서
 * 객체 안에 문자열을 받아올 때 타입변환을 하지않아도 정상적으로 받아오는 것을 확인할 수 있습니다.
 *
 * */

public class BoxExample {

    public static void main(String[] args) {

        // 방법1
        Box1 box1 = new Box1();

        box1.setObject("String");

        String test1 = (String) box1.getObject();

        // 방법2
        Box2<String> box2 = new Box2<String>();

        box2.setT("String");

        String test2 = box2.getT();
    }

}
