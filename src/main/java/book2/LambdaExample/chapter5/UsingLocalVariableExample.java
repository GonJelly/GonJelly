package LambdaExample.chapter5;

public class UsingLocalVariableExample {
    /** 람다식에서 로컬 변수 사용하기
     *
     * 익명 구현 객체에서 매개변수 or 로컬변수를 사용하기 위해서는
     * 변수 앞에 final특성을 가져야 사용할 수 없다. ( final이 붙은 메소드 or 변수는 수정할 수 없다는 뜻!!)
     *
     * 1.5 이루로는 final을 명시적으로 작성하지 않아도 컴파일시 자동으로 인식하기 때문에
     * 꼭 작성할 필요는 없다.
     *
     * 즉, 람다식에서 사용할 매개변수 or 로컬변수는 람다식 나부|외부에서 변경할 수 없다는 것이 된다.
     *
     * */
    public static void main(String[] args) {

        UsingLocalVariable usingVar = new UsingLocalVariable();

        usingVar.method1(20);

    }
}
