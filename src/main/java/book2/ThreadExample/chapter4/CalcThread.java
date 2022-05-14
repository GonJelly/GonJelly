package ThreadExample.chapter4;

public class CalcThread extends  Thread{

    public CalcThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + "을 실행합니다.");
    }

}
