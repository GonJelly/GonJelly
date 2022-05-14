package ThreadExample.chapter7.chapter7_group1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableExample {

    /** 작업 요청 결과 외부에 저장하기
     * Result : 결과값을 저장할 객체
     *
     * 스레드풀을 이용해서 작업을 오쳥할 때 executor() 와 submit() 메소드로 할 수 있다는 것은 앞에서 배웠을 것이다.
     * executor() 예외가 발생하면 해당 스레드를 제거하고 새로운 스레드를 만들어서 스레드 생성에 오버헤더를 발생시킬 수 있어서
     * submit()를 활용하는 것을 선호한다. submit()는 예외가 발생하더라도 해당 스레드를 제거하지않고 다음 작업에 다시 활요하기 때문에
     * 오버헤더의 걱정을 줄일 수 있기 때문이다.
     *
     * submit() 작업을 요청할 때 매객랎으로 Runnable or Callable 를 매개값으로 주게 되는데
     * Runnable를 매개값으로 줄 때
     * case1 : ~.submit(Runnable runnable) 은 리턴값은 null
     * case2 : ~.submit(Runnable runnable, V result) 은 리턴값은 V타입을 리턴한다.
     *
     * Callable을 매개값으로 줄 때
     * Callable을 선언할 때 Callable<V>() callable = new Callable<V>() 제네릭에 작성한 V타입으로 리턴한다.
     *
     * */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        System.out.println("[작업 요청]");
        Result result = new Result();
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);
//        잘못된 코드
//        Future<Result> future1 = executorService.submit(task1, result);
//        Future<Result> future2 = executorService.submit(task2, result);

        try {
            Future<Result> future1 = executorService.submit(task1, result);
            result = future1.get();

            Future<Result> future2 = executorService.submit(task2, result);
            result = future2.get();

            System.out.println("[작업 결과]" + result.getSum());
            System.out.println("[작업 완료]");

        } catch (Exception e) {
            System.out.println("[작업 예외 발생]" + e.getMessage());
            e.printStackTrace();
        }

        executorService.shutdown();

    }

}
