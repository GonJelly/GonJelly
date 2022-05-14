package ReturnExample;

public class Car {

    int gas = 0;

    public void setGas(int gas) {
        this.gas = gas;
    }

    boolean isLeftGas(){

        if(gas == 0){

            System.out.println("가스가 없습니다.");

            return false;
        }

        System.out.println("가스가"+ gas +"남아 있습니다.");

        return true;
    }

    void run(){

        while(true){

            if( gas > 0){
                System.out.println("달립니다. gas가 소비되었습니다.(잔량:"+gas+")");

                gas -= 1;
            } else{
                System.out.println("gas잔량:" + gas + "입니다.");
                return;
            }

        }


    }
}
