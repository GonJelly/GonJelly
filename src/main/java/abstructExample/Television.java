package abstructExample;

public class Television implements RemoteController{

    private int volume;

    @Override
    public void turnOn() {
        System.out.println("TV를 켭니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV를 껐습니다.");
    }

    @Override
    public void setVolume(int volume) {

        if(volume > RemoteController.MAX_VOLUME){
            this.volume = RemoteController.MAX_VOLUME;
        } else if(volume < MIN_VOLUME){
            this.volume = RemoteController.MIN_VOLUME;
        } else{
            this.volume = volume;
        }
        System.out.printf("볼륩은 %d입니다.",this.volume);

    }
}
