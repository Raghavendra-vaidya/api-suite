package com.rkv.utils;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

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
