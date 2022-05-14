package book2.ThreadExample.chapter2;

/** 작업 스레드를 Thread 클래스를 상속받아서 처리하기\ (하위클리스를 이용한 방법)
 *
 * 방법 1
 * Thread를 상속받은 하위 클래스를 객체로 하여 작업 스레드 구현
 *
 * 방법 2
 * Thread를 익명 객체로 작업 스레드를 구현
 *
 * */
public class ThreadExample {

    public static void main(String[] args) {

        // 방법 1
        Thread thread = new WorkerThread();
        thread.start();

        for(int i = 0; i < 5; i++){
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (Exception e){

            }
        }

        // 방법 2
        Thread thread1 = new Thread() {

            @Override
            public void run() {
                // 실행할 코드
            }
        };

//        thread1.start();

    }
}
