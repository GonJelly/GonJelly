package ThreadExample.chapter5;

public class Calculator {

    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {

        synchronized (this) {

            this.money = money;

            try {
                Thread.sleep(2000);
            } catch (Exception e) {}

            System.out.println("작업 스레드 : " + Thread.currentThread().getName() +" \n금액은 " + getMoney());

        }
    }
}
