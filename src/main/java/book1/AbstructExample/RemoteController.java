package book1.AbstructExample;

public interface RemoteController {

    int MAX_VOLUME = 10;
    int MIN_VOLUME = 0;

    void turnOn();
    void turnOff();
    void setVolume(int volume);

    default void setMete(boolean mute){

        if(mute){
            System.out.print("무음 처리합니다.");
        } else{
            System.out.print("무음 해제합니다.");
        }

    }
}
