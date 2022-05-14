package ThreadExample.chapter5;

public class User3 extends Thread{

    private CalculatorSync calculatorSync;

    public void setCalculatorsync(CalculatorSync calculatorSync) {
        setName("User3");
        this.calculatorSync = calculatorSync;
    }

    @Override
    public void run() {

        calculatorSync.setCash(150);

    }
}
