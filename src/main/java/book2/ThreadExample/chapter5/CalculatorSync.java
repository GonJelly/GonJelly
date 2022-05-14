package ThreadExample.chapter5;

public class CalculatorSync {

    private int cash;

    public int getCash() {
        return cash;
    }

    public synchronized void setCash(int cash) {

        this.cash = cash;

        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }

        System.out.println("작업 스레드 : " + Thread.currentThread().getName() + " \n금액은 : " + getCash());
    }
}
