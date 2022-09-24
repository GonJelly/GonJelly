package book2.ThreadExample.chapter1;

public class BeepTask implements Runnable{
    @Override
    public void run() {
        System.out.print("작업 스레드1을 실행하였습니다.");
    }
}
