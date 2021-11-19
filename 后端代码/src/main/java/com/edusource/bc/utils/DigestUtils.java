package com.edusource.bc.utils;

import com.edusource.bc.SM3.SM3Encoder;
import com.edusource.bc.entity.stu_info;

import java.io.IOException;

public class DigestUtils {
    public static String DigestProducer(stu_info stu_info){
        String data = stu_info.toString();
        try{
            return SM3Encoder.byteArrayToHexString(SM3Encoder.hash(data.getBytes()));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
        }
    }

