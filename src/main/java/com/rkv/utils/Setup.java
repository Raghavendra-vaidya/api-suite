package com.rkv.utils;

public class Setup {

    public  static Configuration config = null;

    public static  Configuration getConfigurationObject(){
        return config;
    }

    @BeforeSuite

    public void setup()
    {
        config = new Configuration();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }



}
