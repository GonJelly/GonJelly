package ThreadExample.chapter5;

public class User4 extends Thread{

    private CalculatorSync calculatorSync;

    public void setCalculatorsync(CalculatorSync calculatorSync) {
        setName("User4");
        this.calculatorSync = calculatorSync;
    }

    @Override
    public void run() {

        calculatorSync.setCash(50);

    }
}
