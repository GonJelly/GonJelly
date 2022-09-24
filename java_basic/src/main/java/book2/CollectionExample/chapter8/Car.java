package book2.CollectionExample.chapter8;

public class Car {

    private String name;
    private double rpm;
    private String brand;

    public Car(String name, String brand, double rpm) {
        this.name = name;
        this.brand = brand;
        this.rpm = rpm;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getRpm() {
        return rpm;
    }
}
