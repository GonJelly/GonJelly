package ThreadExample.chapter1;

import java.awt.*;

public class BeepSoundTask implements Runnable{

    @Override
    public void run() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i = 0; i < 5; i++){
            toolkit.beep();
            try {
                Thread.sleep(500);
            } catch(Exception e){

            }
        }
    }
}
