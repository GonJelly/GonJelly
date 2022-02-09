package basicAPIExample.toStringExample;

public class SmartPhoneExample {

    public static void main(String[] args) {

        SmartPhone smartPhone = new SmartPhone("samsung", "android");

        String strObj = smartPhone.toString();
        System.out.println(strObj);
        System.out.println(smartPhone);

    }
}
