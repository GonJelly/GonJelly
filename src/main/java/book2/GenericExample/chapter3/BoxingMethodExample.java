package GenericExample.chapter3;

public class BoxingMethodExample {
    /** 제네릭 메소드
     * 정의 : 리턴타입과 매개변수에 타입 파라미터로 선언된 메소드를 말한다.
     *
     * 선언 방식
     * 리턴타입 앞에 과 메소드의 매개변수에 타입 파라미터를 추가해주면 된다.
     *
     * 제네릭 메소드를 사용해서 값을 받을 때는 메소드 앞에 제네릭으로 리턴타입을 명시하거나
     * 메소드의 매개타입을 보고 컴파일시에 자동으로 인식해주는 2가지 방식이 있다.
     *
     * */
    public static void main(String[] args) {

        Util util = new Util();

        Box<Integer> box1 = util.<Integer>boxing(100);



        Box<String> box2 = util.boxing("홍길동");



    }
}
