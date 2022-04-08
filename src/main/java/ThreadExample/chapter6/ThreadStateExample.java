package ThreadExample.chapter6;

public class ThreadStateExample {

    /** 스레드 상태
     * 스레드의 상태는 크게 실행대기, 실행, 종료, 일시정지 4가지로 볼수 있다.
     * 스레드가 start() 메소드로 실행을 하면 바로 실행되는 것이 아니라 실행대기로 넘어가게된다.
     * 그리고 스케쥴링에 의해서 우선순위에 맞게 스레드가 실행되는 것이다.
     *
     * start() --> 실행대기 <--> 실행 --> 종료 가 되는 것이다.
     * 스레드의 상태는 기본적으로 스케쥴링에 의해서 실행대기, 실행 상태를 run()이 종료될 때까지 번갈아가면서 이루어진다.
     *
     * 그리고 중간에 스레드의 상태를 일시정지 상태로 변경할 수 있는데
     * 일시정지된 스레드를 다시 작업을 한다고해서 실행상태가 되는 것은 아니다.
     * 일시정지 상태에서 실행대기 상태로 넘어가서 실행상태가 되는 것이다.
     * 즉, 실행 --> 일시정지 --> 실행대기 --> 실행 순으로 이루어지는 것이다.
     *
     * (Java 1.5부터)
     * 이러한 스레드의 상태를 보관하는 곳이 있는데 Thread클래스의 getState() 메소드이다.
     * getState() 메소드를 호출하면 타켓 스레드의 상태를 리턴해서 알려준다.
     * 리턴된 값은 State 열거 상수로 리턴되게 된다는 점 유의해야한다.
     *
     * */
    public static void main(String[] args) {

        StatusPrintThread stateThread = new StatusPrintThread(new TargetThread());
        stateThread.start();

    }
}
