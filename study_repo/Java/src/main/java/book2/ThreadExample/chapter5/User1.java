package book2.ThreadExample.chapter5;

public class User1 extends Thread{

    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        setName("User1");
        this.calculator = calculator;
    }

    @Override
    public void run() {

        calculator.setMoney(100);

    }
}
