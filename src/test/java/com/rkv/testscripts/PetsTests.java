package com.rkv.testscripts;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import com.rkv.endpoints.Pets;
import com.rkv.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class PetsTests {

    @Test
    public void createNewPetAndVerify(){
        Date d = new Date();
       String name = "Kitto"+d.getTime();
       JSONObject requestBody=  JsonUtils.getJsonFileObj("src/test/resources/createNewPet.json");
       requestBody.put("name", name);
       System.out.println(requestBody.toString());
       Response response = Pets.createNewPet(requestBody.toString(), 200);
       String actualName = response.jsonPath().get("name").toString();
       String idOfPet = response.jsonPath().get("id").toString();


       Assert.assertEquals(name,actualName );
       Assert.assertFalse(idOfPet.isEmpty());


    }

    @Test
    public void verifyPetByID(){
        Date d = new Date();
        String name = "Kitto"+d.getTime();

        JSONObject requestBody=  JsonUtils.getJsonFileObj("src/test/resources/createNewPet.json");
        requestBody.put("name", name);
        System.out.println(requestBody.toString());
        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();

        Response resp = Pets.getPetByID(idOfPet, 200);
        Assert.assertEquals("Booom pets", resp.jsonPath().get("name"));
    }
}
