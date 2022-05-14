package ThreadExample.chapter1;

import java.awt.*;
/** '띵' 하는 소리와 '띵'프린팅을 동시해 하도록 작업을 해보시오
 * 방법 1은 잘못된 예시에 대해서 작성한 것입니다.
 * 문제점 : '띵'소리와 '띵'프린팅하는 것이 동시에 이루어지지않고 소리 다음에 프린팅이된다.
 *
 * 방법 2 main 스레드에서는 '띵'프린팅만 하고 '띵'소리는 다른 작업 스레드에서 하도록 처리한다.
* */
public class BeepTaskExample {

    public static void main(String[] args) {

        // 방법 1
/*

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i = 0; i < 5; i++){
            toolkit.beep();
            try {
                Thread.sleep(500);
            } catch(Exception e){

            }
        }
*/

        // 방법 2
        Runnable beepSoundTask = new BeepSoundTask();
        Thread threadTask1 = new Thread(beepSoundTask);
        threadTask1.start();

        for(int i = 0; i < 5; i++){
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (Exception e){

            }
        }
    }
}
