package com.ssafy.Util;

import java.util.Random;

public class StringUtil {

    private String randomPwd() {
        int leftLimit = 47;
        int rightLimit = 128;
        int totalLimit = 12;
        Random random = new Random();
        String generator = random.ints(leftLimit, rightLimit - 1)
                .limit(totalLimit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generator;
    }
}
