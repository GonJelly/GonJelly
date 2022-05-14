package ThreadExample.chapter7.chapter7_group1;

public class Task implements Runnable{

    Result result;

    public Task(Result result) {
        this.result = result;
    }

    @Override
    public synchronized void run() {

        int sum = 0;

        for(int i = 1; i <= 10; i++) {
            sum += i;
        }

        result.setSum(sum);
    }
}
