package com.rkv.testscripts;

import io.restassured.response.Response;
import com.rkv.endpoints.Users;
import com.rkv.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class UserTest {

    Date date;

    @Test(description = "verify create user api")
    public void createUser(){
        date = new Date();
        JSONObject requestBody = JsonUtils.getJsonFileObj("src/test/resources/createSingleUser.json");
        String userName = "User"+date.getTime();
        String firstName = userName;
        String lastName = "boogie";
        String phoneNumber = "1"+date.getTime();
        String email = userName+"@gmail.com";
        String password = userName;
        requestBody.put("username",userName);
        requestBody.put("firstName",firstName);
        requestBody.put("lastName",lastName);
        requestBody.put("email",email);
        requestBody.put("password",password);
        requestBody.put("phone",phoneNumber);

        System.out.println(requestBody.toString());

        /***^Data preparation**/

        Response createUserResponse=Users.createUser(requestBody.toString(), 200);
        System.out.println(createUserResponse.body().asString());
        /***^Api call**/

        Assert.assertFalse(createUserResponse.jsonPath().get("message").toString().isEmpty());

        /***^Assertions**/
    }

    @Test(description = "verify get user api")
    public void getUserByUsername(){
        date = new Date();
        JSONObject requestBody = JsonUtils.getJsonFileObj("src/test/resources/createSingleUser.json");
        String userName = "User"+date.getTime();
        String firstName = userName;
        String lastName = "boogie";
        String phoneNumber = "1"+date.getTime();
        String email = userName+"@gmail.com";
        String password = userName;
        requestBody.put("username",userName);
        requestBody.put("firstName",firstName);
        requestBody.put("lastName",lastName);
        requestBody.put("email",email);
        requestBody.put("password",password);
        requestBody.put("phone",phoneNumber);

        Response createUserResponse=Users.createUser(requestBody.toString(), 200);
        /***^Data preparation**/


        Response getResponse = Users.getUser(userName, 200);

        System.out.println(getResponse.body().asString());

        /***^API call**/

        String actualusername = getResponse.jsonPath().get("username").toString();
        String id = getResponse.jsonPath().get("id").toString();
        String actualEmail = getResponse.jsonPath().get("email").toString();
        /***^Assertions**/

        Assert.assertEquals(userName,actualusername);
        Assert.assertEquals(email,actualEmail);
        Assert.assertFalse(id.isEmpty());
    }

}
