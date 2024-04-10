package com.moretto.bruno.store.utils;

import java.util.Random;

public class StringUtils {

    public static String generateRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&*()-[]{};,";

        while (sb.length() < size) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

}
