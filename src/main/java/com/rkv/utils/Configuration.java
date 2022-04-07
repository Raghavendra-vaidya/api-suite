package com.rkv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public  String HOST;

    public Configuration()  {
        Properties prop = new Properties();
        File file =  new File("src/main/resources/config.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            prop.load(fileInputStream);
            this.HOST = prop.getProperty("HOST");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
