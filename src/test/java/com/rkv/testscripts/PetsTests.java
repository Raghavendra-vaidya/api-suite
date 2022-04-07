package com.rkv.testscripts;

import com.jayway.restassured.response.Response;
import com.rkv.endpoints.Pets;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetsTests {


    @Test
    public void verifyPetByID(){
        Response resp = Pets.getPetByID("9223372000001100234", 200);
        Assert.assertEquals("Booom pets", resp.jsonPath().get("name"));
        Assert.assertEquals("Cat family", resp.jsonPath().get("category.name"));
    }
}
