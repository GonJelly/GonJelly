package ThreadExample.chapter7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/** 스레드풀 작업 생성 및 요청 처리
 *
 * */
public class ExecutorExample {

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 10; i++) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드의 총 개수 및 작업 스레드 이름 출력하기
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("현재 작업 중인 스레드 크기 : " + poolSize + "\t스레드 이름 :" + threadName);

                    // 일부러 예외처리 발생시킴
                    int value = Integer.parseInt("삼");

                }
            };

//            executorService.execute(runnable);
            executorService.submit(runnable);

            Thread.sleep(10);
        }

        executorService.shutdown();

    }
}
