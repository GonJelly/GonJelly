package abstructExample;

public class Audio implements RemoteController{

    private int volume;

    @Override
    public void turnOn() {
        System.out.println("Audio를 켰습니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("Audio를 껐습니다.");
    }

    @Override
    public void setVolume(int volume) {

        if(volume > RemoteController.MAX_VOLUME){
            this.volume = RemoteController.MAX_VOLUME;
        } else if(volume < RemoteController.MIN_VOLUME) {
            this.volume = RemoteController.MIN_VOLUME;
        } else{
            this.volume = volume;
        }

        System.out.printf("현재 볼륨은 %d입니다.",this.volume);

    }

    @Override
    public void setMete(boolean mute) {
        RemoteController.super.setMete(mute);
    }
}
