package ThreadExample.chapter6;

public class ThreadStateExample {

    public static void main(String[] args) {

        StatusPrintThread stateThread = new StatusPrintThread(new TargetThread());
        stateThread.start();

    }
}
