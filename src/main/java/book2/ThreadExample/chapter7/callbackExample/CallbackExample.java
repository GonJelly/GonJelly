package ThreadExample.chapter7.callbackExample;

import javax.security.auth.callback.CallbackHandler;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {

    /** 콜백 방식으로 작업 오청 통보
     * ====================================================
     * 객체 생성과 동시에 스레드풀을 생성하고
     *
     * completionHandler 인터페이스를 이용하여 콜백 객체 생성
     * completed() 성송 시 작업코드 작성
     * failed() 실패 시 작업코드 작성
     *
     * 이슈를 처리할 메소드 생성 doWork()
     * =====================================================
     * 자바에서 콜백 함수를 구현하고 싶다면 NIO패키지에 있는
     * CompletionHandler 인터페이스를 이용해서 구현하면 된다.!!
     *
     * 콜백 방식
     * 메인 스레드가 스레드풀에 작업 요청을 하면 멈추는 잏 없이
     * 다른 기능을 수행할 수 있는 방식을 말한다.
     *
     * 즉, 메인 스레드에서 스레드풀로 작업 요청(executor(), submit())를 하면
     * 결과를 기다리지 않고 다른 작업을 바로 실행할 수 있다는 점이다.
     *
     * */
    private ExecutorService executorService;

    public CallbackExample() {
        this.executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {

        @Override
        public void completed(Integer result, Void attachment) {
            System.out.println("Completed() 실행: " + result);
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            System.out.println("failed() 실행: " + exc.getMessage());
        }
    };

    public void doWork(final String x, final String y) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int intX = Integer.parseInt(x);
                    int intY = Integer.parseInt(y);

                    int result = intX + intY;

                    callback.completed(result, null);
                } catch (Exception e) {
                    callback.failed(e, null);
                }
            }
        };

        executorService.submit(runnable);
    }

    public void finish() {
        executorService.shutdown();
    }

    public static void main(String[] args) {

        CallbackExample callbackExample = new CallbackExample();
        callbackExample.doWork("3", "3");
        callbackExample.doWork("3", "삼");
        callbackExample.finish();

    }

}
