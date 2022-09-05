package com.rkv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GetData {

    public static String fromProperties(String filePath, String key){
        File file = new File(filePath);
        String data=null;
        try{
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
            data = prop.getProperty(key);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }


}
