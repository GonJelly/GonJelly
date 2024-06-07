package book2.ThreadExample.chapter2;

import java.awt.*;

public class WorkerThread extends Thread{

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
