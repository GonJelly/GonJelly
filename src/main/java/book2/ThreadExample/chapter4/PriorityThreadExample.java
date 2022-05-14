package ThreadExample.chapter4;

public class PriorityThreadExample {
    /** 스레드의 우선순위
     * 스레드는 싱글 코에에서 멀티태스킹을 실행하면 동시성을 가지고 작업을 실행한다.
     * 동시성 : 하나의 코어에서 여러 스레드를 번갈아 가면서 작업을 실행하는 특성
     *
     * 멀티 코어에서 멀티태스킹을 실행하면 병행성을 가지고 작업을 실행한다.
     * 단, 코어의 수보다 작업스레드의 갯 수가 많으면 동시성을 가지고 작업을 실행한다.
     * 병행성 : 코어 하나가 하나의 스레드를 가지고 동시에 여러 스레드를 실행하는 특성
     *
     * 둘다 여러 스레드를 동시에 작업을 한다는 방면에서는 비슷하지만 처리하는 방식이 다르다.!!!
     *
     * 동시성을 가진 스레드끼리는 스케쥴링을 통해서 순서을 정하는데 크게 두 가지가 있다.
     * 1. 우선순위(Priority) 스케쥴링
     * 2. 순서 할당(Round Robin) 스케줄링
     *
     * 우선순위 스케쥴링은 Thread클래스 내부에 setPriority() 메소드를 이용해서 순서를 정해줄 수 있다.
     * setPriority()의 매개값으로는 숫자가 들어가고 1~10 까지 순으로
     * 1이 가장 우선순위가 낮고 10은 가장 우선순위가 높다고 생각하면 됩니다.
     * 숫자대신 상슈를 넣을 수도 있다. ( MAX~ , NOMAL~, MIN~ )
     *
     * */
    public static void main(String[] args) {

        for(int i = 1; i <= 10; i++){

            Thread thread = new CalcThread("thread" + i);

            if( i != 10) {
                thread.setPriority(Thread.MIN_PRIORITY);
            } else {
                thread.setPriority(Thread.MAX_PRIORITY);
            }

            thread.start();

        }

    }
}
