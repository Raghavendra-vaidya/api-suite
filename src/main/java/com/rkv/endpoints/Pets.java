package com.rkv.endpoints;

import com.jayway.restassured.response.Response;
import com.rkv.utils.Configuration;
import static com.jayway.restassured.RestAssured.given;

public class Pets {
    static Configuration conf = new Configuration();

    public static Response getPetByID(String id, int statusCode){

        String endPoint = conf.HOST+"/pet/"+id;

        Response apiResponse=given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return apiResponse;
    }




}
