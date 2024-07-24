package com.movie.test.common.cef;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDCustom {

    public static String createUUID(){
        String orgUUID = UUID.randomUUID().toString();
        byte[] orgUUIDBytes = orgUUID.getBytes(StandardCharsets.UTF_8);
        byte[] hashBytes;

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hashBytes = messageDigest.digest(orgUUIDBytes);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

        StringBuilder stringBuilder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
        for(int i=0; i<5; i++){
            stringBuilder.append(String.format("%02x", hashBytes[i]));
        }

        return stringBuilder.toString();
    }
}
