package ThreadExample.chapter7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {

    /** 리턴값 없는 작업 완료 통보
     * */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
              int sum = 0;
              
              for(int i = 1; i <= 10; i++){
                  sum += i;
              }

                System.out.println(sum);
            }
        };
        
        Future future = executorService.submit(runnable);
        
        try {
            future.get();
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            System.out.println("[작업 예외 처리]]" + e.getMessage());
        }

        executorService.shutdown();
        
    }
}
