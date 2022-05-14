package book1.BasicAPIExample.Object.toStringExample;

public class SmartPhone {

    private String comopany;
    private String os;

    public SmartPhone(String comopany, String os) {
        this.comopany = comopany;
        this.os = os;
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "comopany='" + comopany + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
