package com.rkv.testscripts;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import com.rkv.endpoints.Users;
import com.rkv.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class UserTest {

    Date date;

    @Test(description = "verify create user api")
    public void createUser(){
        /***Data preparation**/
        date = new Date();
        JsonObject requestBody = JsonUtils.getJsonFileObject("src/test/resources/createSingleUser.json");
        String userName = "User"+date.getTime();
        String firstName = userName;
        String lastName = "boogie";
        String phoneNumber = "1"+date.getTime();
        String email = userName+"@gmail.com";
        String password = userName;
        requestBody.addProperty("username",userName);
        requestBody.addProperty("firstName",firstName);
        requestBody.addProperty("lastName",lastName);
        requestBody.addProperty("email",email);
        requestBody.addProperty("password",password);
        requestBody.addProperty("phone",phoneNumber);

        /***Api call**/
        Response createUserResponse=Users.createUser(requestBody.toString(), 200);

        /***Assertions**/
        Assert.assertFalse(createUserResponse.jsonPath().get("message").toString().isEmpty());
    }

    @Test(description = "verify get user api")
    public void getUserByUsername(){
        /***Data preparation**/
        date = new Date();
        JsonObject requestBody = JsonUtils.getJsonFileObject("src/test/resources/createSingleUser.json");
        String userName = "User"+date.getTime();
        String firstName = userName;
        String lastName = "boogie";
        String phoneNumber = "1"+date.getTime();
        String email = userName+"@gmail.com";
        String password = userName;
        requestBody.addProperty("username",userName);
        requestBody.addProperty("firstName",firstName);
        requestBody.addProperty("lastName",lastName);
        requestBody.addProperty("email",email);
        requestBody.addProperty("password",password);
        requestBody.addProperty("phone",phoneNumber);

        /***API call**/
        Response createUserResponse=Users.createUser(requestBody.toString(), 200);
        Response getResponse = Users.getUser(userName, 200);

        String actualusername = getResponse.jsonPath().get("username").toString();
        String id = getResponse.jsonPath().get("id").toString();
        String actualEmail = getResponse.jsonPath().get("email").toString();

        /***Assertions**/
        Assert.assertEquals(userName,actualusername);
        Assert.assertEquals(email,actualEmail);
        Assert.assertFalse(id.isEmpty());
    }

}
