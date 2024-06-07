package book2.LambdaExample.chapter1;

public class MyFunctionalInterfaceExample {
    /** 리턴값이 없는 람다식 표현하기
     *
     * 필요 지식
     * 함수적 프로그래밍 - 병렬 처리, 이벤트 지향 프로그래밍에 적합하다.!!
     *
     * 람다식
     * 정의 : 익명 함수(Anonymous function)을 생성하기 위한 식
     * 1. 익명 구현 객체를 좀 더 간결하게 표현할 수 있어짐
     * 2. 컬렉션 요소를 필터링 or 맵핑해서 원하는 결과를 쉽게 집계할 수 있게됨
     *
     * 주의할 점
     * 1. 추상 메소드가 두 개 이상 선언된 인터페이스에서는 사용할 수 없다.!!
     * 2. 추상 메소드가 하나뿐인 인터페이스 == 함수적 인터페이스 여야한다는 점!
     * 만약, 컴파일시에 함수적 인터페이스를 검사하고 싶다면 @FunctionalInterface 어노테이션을 사용하면 된다
     *
     * 표현방법
     * 기본 : (매개변수, ..) -> { 실행코드 }
     * 런타임시 매개변수의 타입을 인식해주기 때문에 꼭!! 매개변수 타입을 지정해줄 필요는 없음
     * 매개변수가 하나라면 소괄호'()'는 생략가능, 매개변수가 없다면 소괄호'()'는 반드시 작성해줘야한다.!
     * 실행코드가 한줄뿐이라면 중괄호'{}'는 생략가능
     * 리턴값이 있다면 return문을 선언해주면 되는데 만약 코드가 return문만 있다면 return문은 생략하고 리턴값만 대입하면 된다.
     *
     * */
    public static void main(String[] args) {

        MyFunctionalInterface fi = () -> {
            String str = "method call1";
            System.out.println(str + " 시작합니다.");
        };
        fi.method();

        // {}부호 생략 하지않음
        fi = () -> { System.out.println("method call2"); };
        fi.method();

        // {}부호 생략 작성
        fi = () -> System.out.println("method call3");
        fi.method();

    }

}
