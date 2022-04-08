package ThreadExample.chapter7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {
    /** 리턴값 있는 작업 완료 통보
     *
     * */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                int sum = 0;

                for(int i = 1; i <= 10; i++){
                    sum += i;
                }

                return sum;
            }
        };

        Future<Integer> future = executorService.submit(callable);

        try {
            int sum = future.get();
            System.out.println("[작업 결과] " + sum);
            System.out.println("[작업 완료] ");
        } catch(Exception e) {
            System.out.println("[작업 도중 예외 발생]" + e.getMessage());
            e.printStackTrace();
        }

        executorService.shutdown();

    }
}
