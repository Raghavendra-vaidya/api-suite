package com.rkv.endpoints;

import com.rkv.utils.Configuration;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

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


    public static Response createNewPet(String body, int statusCode){

        String endPoint = conf.HOST+"/pet";

        Response apiResponse=given().log().ifValidationFails()
                .header("Content-Type", "application/json").body(body)
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return apiResponse;
    }

    public static Response updatePet(String body, int statusCode){

        String endPoint = conf.HOST+"/pet";

        Response apiResponse=given().log().ifValidationFails()
                .header("Content-Type", "application/json").body(body)
                .when().put(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return apiResponse;
    }

    public static Response uploadPetImage(String petID,String filePath, int statusCode){

        String endPoint = conf.HOST+"/pet/"+petID+"/uploadImage";

        Response apiResponse=given().log().ifValidationFails()
                .header("Content-Type", "multipart/form-data")
                .accept("application/json")
                .multiPart("file", new File(filePath))
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return apiResponse;
    }

    public static Response findPetByStatus(String status, int statusCode){

        String endPoint = conf.HOST+"/pet/findByStatus?status="+status;

        Response apiResponse=given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return apiResponse;
    }



}
