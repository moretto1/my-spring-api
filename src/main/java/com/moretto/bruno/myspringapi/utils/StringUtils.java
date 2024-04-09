package com.moretto.bruno.myspringapi.utils;

import java.util.Random;

public class StringUtils {

    public static String generateRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&*()-[]{};,/";

        while (sb.length() < size) { // length of the random string.
            int index = random.nextInt() * chars.length();
            sb.append(chars.charAt(index));
        }
        return sb.toString();

    }

}
