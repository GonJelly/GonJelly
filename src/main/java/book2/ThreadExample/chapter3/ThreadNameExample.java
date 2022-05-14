package ThreadExample.chapter3;

public class ThreadNameExample {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        System.out.println("프로그램 시작 스레드 이름 :" + mainThread.getName());

        Thread threadA = new ThreadA();
        threadA.start();

        Thread threadB = new ThreadA();
        threadB.start();

    }
}
