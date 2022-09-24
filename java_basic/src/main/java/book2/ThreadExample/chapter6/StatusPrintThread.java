package book2.ThreadExample.chapter6;

public class StatusPrintThread extends Thread {

    private TargetThread targetThread;

    public StatusPrintThread(TargetThread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {

        while(true) {

            Thread.State state = targetThread.getState();
            System.out.println("작업 스레드의 상태 : " + state);


            if(state == Thread.State.NEW) {
                targetThread.start();
            }

            if(state == Thread.State.TIMED_WAITING) {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}

        }
    }
}
