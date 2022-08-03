package com.rkv.testscripts;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import com.rkv.endpoints.Pets;
import com.rkv.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;


public class PetsTest {
    Date date;

    @Test (description = "Create new pet and verify the creation")
    public void createNewPetAndVerify(){
        date = new Date();
       String name = "Kitto"+date.getTime();
       JsonObject requestBody=  JsonUtils.getJsonFileObject("src/test/resources/createNewPet.json");

       requestBody.addProperty("name", name);

       Response response = Pets.createNewPet(requestBody.toString(), 200);

       String actualName = response.jsonPath().get("name").toString();
       String idOfPet = response.jsonPath().get("id").toString();

       Assert.assertEquals(name,actualName );
       Assert.assertFalse(idOfPet.isEmpty());
    }

    @Test (description = "Verify pet by id")
    public void verifyPetByID(){
        date = new Date();
        String name = "Kitto"+date.getTime();
        JsonObject requestBody=  JsonUtils.getJsonFileObject("src/test/resources/createNewPet.json");
        requestBody.addProperty("name", name);

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();

        Response resp = Pets.getPetByID(idOfPet, 200);
        Assert.assertEquals(name, resp.jsonPath().get("name"));
    }

    @Test (description = "verify updating of a pet name, family and tag name")
    public void verifyUpdatePet(){
        /*** Data preparation***/
        date = new Date();
        String name = "Kitto"+date.getTime();
        String expectedName = "Pet 1";
        String expectedtagName = "updated_pet";
        String expectedFamily = "Big cats";
        JsonObject requestBody=  JsonUtils.getJsonFileObject("src/test/resources/createNewPet.json");
        requestBody.addProperty("name", name);

        /*** Create Pet for updating ***/
        Response response = Pets.createNewPet(requestBody.toString(), 200);

        /*** data preparation for updating ***/
        JsonObject requestBodyUpdate=  JsonUtils.getJsonFileObject("src/test/resources/UpdatePet.json");
        requestBodyUpdate.addProperty("id", response.jsonPath().get("id").toString());

        /*** update api call ***/
        Response updateResponse=Pets.updatePet(requestBodyUpdate.toString(), 200);

        /*** Update api test assertions ***/
        Assert.assertEquals(expectedName, updateResponse.jsonPath().get("name").toString());
        Assert.assertEquals(expectedtagName, updateResponse.jsonPath().get("tags[0].name").toString());
        Assert.assertEquals(expectedFamily, updateResponse.jsonPath().get("category.name").toString());
    }

    @Test (description = "verify updating of a pet image*")
    public void updatePetImage(){
        date = new Date();
        String name = "Kitto"+date.getTime();
        JsonObject requestBody=  JsonUtils.getJsonFileObject("src/test/resources/createNewPetWithoutImage.json");
        requestBody.addProperty("name", name);

        Response createResponse = Pets.createNewPet(requestBody.toString(), 200);
        String petId = createResponse.jsonPath().get("id").toString();

        Pets.uploadPetImage(petId,"src/test/resources/Kitty.jpg",200 );

        Response getPet = Pets.getPetByID(petId, 200);
        Assert.assertFalse(getPet.jsonPath().get("photoUrls").toString().isEmpty());
    }

    @Test(description = "Verify get pet by status")
    public void getPetByStatus(){
        Response response = Pets.findPetByStatus("available", 200);
        List<String> status = response.jsonPath().get("status");
        Boolean isTrue = true;

        for (String s:status) {
            if (!s.equalsIgnoreCase("available")){
                isTrue = false;
                break;
            }
            else continue;
        }

        Assert.assertTrue(isTrue);
    }

}
