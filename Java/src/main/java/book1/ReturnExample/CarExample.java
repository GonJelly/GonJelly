package book1.ReturnExample;

public class CarExample {

    public static void main(String[] args) {

        Car myCar = new Car();

        myCar.run();

        myCar.setGas(10);

        boolean gasState = myCar.isLeftGas();
        if(gasState){
            System.out.println("출발합니다.");
            myCar.run();
        }

        if(myCar.isLeftGas()){
            System.out.println("gas를 주입할 필요가 없습니다.");
        } else{
            System.out.println("gas를 주입해야합니다.");
        }
    }
}
