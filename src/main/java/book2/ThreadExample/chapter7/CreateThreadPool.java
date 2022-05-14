package ThreadExample.chapter7;

import java.util.concurrent.*;

public class CreateThreadPool {

    public static void main(String[] args) {

        /** 스레드풀 생성하기
         * 방법1
         * */

        // 방법 1
        ExecutorService threadPoolExample = Executors.newCachedThreadPool();

        // 방법 2
        ExecutorService threadPoolExample2 = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        // 방법 3
        ExecutorService threadPoolExample3 = new ThreadPoolExecutor(
                3,
                100,
                120L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()
        );
    }
}
