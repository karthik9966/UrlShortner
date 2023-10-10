package com.ttu.urlShortner.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashGenerator {

    private static String defaultText = "http://localhost:8081/";

    public static String hash(String input, String algorithm, int length) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        String truncatedHash = hexString.toString().substring(0, length);
        return defaultText+truncatedHash;
    }
}

