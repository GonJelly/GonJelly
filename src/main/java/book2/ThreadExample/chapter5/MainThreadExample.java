package ThreadExample.chapter5;

public class MainThreadExample {

    /** [이것이 자바다]동기화 메소드와 동기화 블록
     *  다른 스레드에서 공유 객체를 사용할 때는 주의를 해야한다.!!
     *
     *  스레드1에서 모객체에 100을 넣었다가 잠시 일시정지된 사이에
     *  스레드2에서 모객체에 50을 대입하면 스레드1에서 출력되는 결과는 달라질 수 있다.
     *
     *  즉, 공유객체를 사용할 때는 하나의 스레드만 접근이 가능하도록 해야한다.
     *  메소드 선언시 앞에 Synchronized 를 붙여주면 동기화해서 사용하겠다는 뜻
     *  or 특정 코드만 동기화하고 싶다면 Synchronized 블록을 이용할 수도 있다.
     *
     *  참조는 아래 코드를 참조!!
     *
     * */
    public static void main(String[] args) {

        // 동기화 블록을 이용한 방법
        Calculator calculator = new Calculator();

        User1 user1 = new User1();
        user1.setCalculator(calculator);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator(calculator);
        user2.start();

        // 동기화 메소들를 이용한 방법
        CalculatorSync calculatorSync = new CalculatorSync();

        User3 user3 = new User3();
        user3.setCalculatorsync(calculatorSync);
        user3.start();

        User4 user4 = new User4();
        user4.setCalculatorsync(calculatorSync);
        user4.start();


    }
}
