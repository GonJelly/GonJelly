package com.ssafy.Util;

import java.util.Random;

public class PrivateUtil {

    public static String randomPwd() {
        int leftLimit = 48;
        int rightLimit = 122;
        int totalLimit = 12;
        Random random = new Random();
        String generator = random.ints(leftLimit, rightLimit - 1)
                .filter(num -> (num <= 57 || num >= 65) && ( num <= 90 || num >= 97))
                .limit(totalLimit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generator;
    }

    public static String phoneNumber(String number) {

        if( number.length() == 11 ) {
            StringBuilder phone = new StringBuilder();
            phone.append(number.substring(0,3));
            phone.append("-");
            phone.append(number.substring(3,7));
            phone.append("-");
            phone.append(number.substring(7));
            return phone.toString();
        }

        return "010-0000-0000";
    }
}
