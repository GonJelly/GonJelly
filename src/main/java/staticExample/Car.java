package staticExample;

public class Car {
    
    int speed;
    
    void run(){
        System.out.println("달리기 시작했습니다.");
    }

    // static으로 선언된 static멤버는 인스턴스 멤버를 사용할 수 없다.
    public static void main(String[] args) {
        
        /* 잘못된 예시
            speed = 60;
            run();
        */
        
        // 올바른 예시
        Car myCar = new Car();
        
        myCar.speed = 60;
        myCar.run();

    }

    void myCarName(String name){


        speed = 100;
        run();

    }
    
}
