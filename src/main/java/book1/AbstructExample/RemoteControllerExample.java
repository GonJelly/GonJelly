package AbstructExample;

public class RemoteControllerExample {

    public static void main(String[] args) {

        RemoteController rc;

        rc = new Television();

        rc.turnOn();
        rc.turnOff();


        rc = new Audio();

        rc.turnOn();
        rc.turnOff();

    }
}
