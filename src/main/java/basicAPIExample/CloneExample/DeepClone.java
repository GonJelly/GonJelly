package basicAPIExample.CloneExample;

import java.util.Arrays;

public class DeepClone implements Cloneable{

    public String id = "";
    public String name = "";
    public int age = 0;
    public int[] score;
    public Car car;

    public DeepClone(String id, String name, int age, int[] score, Car car) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.car = car;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        DeepClone cloned = (DeepClone) super.clone();

        cloned.score = Arrays.copyOf(this.score, this.score.length);

        cloned.car = new Car(this.car.model);

        return cloned;
    }

    public DeepClone getDeepClone(){
        DeepClone cloned = null;
        try {
            cloned = (DeepClone) clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return cloned;
    }
}
