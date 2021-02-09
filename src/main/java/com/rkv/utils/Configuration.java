package com.rkv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public  String url;

    public Configuration(){
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("./src/main/resources/constants.properties");

            prop.load(file);
            this.url = prop.getProperty("URL");
        }
        catch (IOException i){
            
            i.printStackTrace();
        }


    }


}
