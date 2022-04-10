package com.rkv.endpoints;

import com.jayway.restassured.response.Response;
import com.rkv.utils.Configuration;


import static com.jayway.restassured.RestAssured.given;

public class Users {

    static Configuration conf = new Configuration();

    public static Response createUser(String reqBody, int statusCode){
        String endPoint = conf.HOST+"/user";
        Response response = given().log().ifValidationFails()
                        .header("Content-Type","application/json")
                        .accept("application/json").body(reqBody)
                        .when().post(endPoint)
                        .then().
                        assertThat()
                        .statusCode(statusCode).extract().response();

        return response;

    }

    public static Response createUserWithList(String reqBody, int statusCode){
        String endPoint = conf.HOST+"/user/createWithList";
        Response response = given().log().ifValidationFails()
                            .header("Content-Type","application/json")
                            .accept("application/json").body(reqBody)
                            .when().post(endPoint)
                            .then()
                            .assertThat()
                            .statusCode(statusCode).extract().response();

        return response;

    }

    public static Response createUserWithArray(String reqBody, int statusCode){
        String endPoint = conf.HOST+"/user/createWithArray";
        Response response =given().log().ifValidationFails()
                        .header("Content-Type","application/json")
                        .accept("application/json").body(reqBody)
                        .when().post(endPoint)
                        .then().
                        assertThat()
                        .statusCode(statusCode).extract().response();

        return response;

    }
    public static Response get(String userName, int statusCode){
        String endPoint = conf.HOST+"/user/"+userName;
        Response response = given().log().ifValidationFails()
                        .header("Content-Type","application/json")
                        .accept("application/json")
                        .when().get(endPoint)
                        .then().
                        assertThat()
                        .statusCode(statusCode).extract().response();

        return response;

    }

    public static Response updateUserDetails(String userName,String reqBody, int statusCode){
        String endPoint = conf.HOST+"/user/"+userName;
        Response response = given()
                        .log().ifValidationFails()
                        .header("Content-Type","application/json")
                        .accept("application/json").body(reqBody)
                        .when().put(endPoint)
                        .then().
                        assertThat()
                        .statusCode(statusCode).extract().response();

        return response;

    }

    public static Response deleteUser(String userName, int statusCode){
        String endPoint = conf.HOST+"/user/"+userName;
        Response response = given()
                        .log().ifValidationFails()
                        .header("Content-Type","application/json")
                        .accept("application/json")
                        .when().delete(endPoint)
                        .then().
                        assertThat()
                        .statusCode(statusCode).extract().response();

        return response;

    }

    public static Response userLogin(String userName,String password, int statusCode){
        String endPoint = conf.HOST+"/user/login?username="+userName+"&password="+password;
        Response response = given()
                .log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().get(endPoint)
                .then().
                assertThat()
                .statusCode(statusCode).extract().response();

        return response;

    }




}
