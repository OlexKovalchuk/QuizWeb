package com.quiz.controller.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public static String encrypt(String pass) throws NoSuchAlgorithmException {
        if (pass == null) return null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(pass.getBytes());
        byte[] hash = digest.digest();
        StringBuilder answerHashSB = new StringBuilder("");
        String[] hexHash = new String[hash.length];
        for (int i = 0; i < hash.length; i++) {
            hexHash[i] = Integer.toHexString(hash[i]);
            if (hexHash[i].length() == 1) {
                answerHashSB.append('0');
            }
            answerHashSB.append(hexHash[i]);
        }
        String answerHash = answerHashSB.toString();
        answerHash = answerHash.replaceAll("ffffff", "");
        return answerHash.toUpperCase();
    }
}
