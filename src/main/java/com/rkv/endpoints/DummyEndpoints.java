package com.rkv.endpoints;

import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

public class DummyEndpoints {


    public  static Response getEmployees(String url, int responseCode){
        String endPoint = url+"/api/v1/employees";
        Response response = given().log().ifValidationFails()
                            .header("Content-Type","application/json")
                            .when()
                            .get(endPoint)
                            .then().assertThat().statusCode(responseCode).extract().response();
        return response;
    }


}
