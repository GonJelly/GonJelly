package ThreadExample.chapter1;

public class TaskExample {

    /**
     * 작업 스레드 생성하기 예제 1
     * 작업 스레드를 생성하기 위해서는 Runnable 인터페이스로 구현한 클래스를 이용해서 인스턴스를 생성하고
     * 해당 인스턴스를 Thread 생성자의 매개값으로 주어서 Thread 객체를 생성하는 방법이다.
     *
     * 작업 스레드 생성하기 예제 2 (많이 사용되는 방법)
     * Runnable 인터페이스를 인스턴스로 해서 매개값으로 전달하지 말고
     * 익명 객체로 해서 직접 코드에 넣는 방법
     *
     * 방법 2를 사용하는 이유는 코드를 절약하기 위해서 이다.
     *
     * 작업 스레드 생성하기 예제 3
     * 방법 2는 익명객체를 사용해서 스레드를 생헝하는 방법이였다. 즉, 함수적 인터페이스인 것이다.
     * 방법 2를 커스텀이 가능한데 그것은 람다식을 사용하는 것이다.
     * */
    public static void main(String[] args) {

        // 방법 1
        Runnable beepTask = new BeepTask();
        Thread task = new Thread(beepTask);

        // 방법 2
        Thread task2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("작업 스레드2를 실행하였습니다.");
            }
        });

        // 앙법 3
        Thread task3 = new Thread( () -> {
            System.out.println("작업 스레드3를 실행하였습니다.");
        });

        /**
         * 위와 같이 생성된 스레드를 실행하기 위해서는 start() 메소드를 호출해야만 비로소 실행된다.
        * */

        task.start();
        task2.start();
        task3.start();

    }
}
