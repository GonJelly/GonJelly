package LambdaExample.chapter4;


/** 클래스 멤버 사용하는 람다식
 *
 * this는 클래스 멤버의 이름이 메소드에서 사용되는 매개변수의 이름과 켭칠 때
 * 클래스 멤버변수를 메소드에서 사용하려할 때 많이 사용되는데
 *
 * 람다식에서조금 다르다.
 *
 * 람다식에서 this를 사용하게 되면 this가 참조하는 객체는 람다식을 실행한 클래스 객체를 참조하게 된다.
 * 그래서 만약 중첩클래스를 사용하고 중첩클래스 안에 람다식을 사용하게 된다면
 * 람다식에 있는 this는 충첩클래스의 객체를 참조하게 된다는 것이다.
 *
 * 정확한 설명은 아래 코드를 보면서 이해하면 좋을거 같습니다.
 *
 * */
public class MyfunctionalInterfaceExample {

    public static void main(String[] args) {

        UsingThis usingThis = new UsingThis();

        UsingThis.Inner inner = usingThis.new Inner();

        inner.method();

    }
}
