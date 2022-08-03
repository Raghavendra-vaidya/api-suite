package com.rkv.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonUtils {


    public static JsonObject getJsonFileObject(String filePath) {
        JsonObject object = null;
        Path path = Paths.get(filePath);

        try {
            Reader reader = Files.newBufferedReader(path);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            object = jsonElement.getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static JsonArray getJsonFileArray(String filePath) {
        JsonArray jsonArray = null;
        Path path = Paths.get(filePath);
        try {
            Reader fileReader = Files.newBufferedReader(path);
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            jsonArray = jsonElement.getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static String getJsonObjectAsString(JsonObject jsonObj){
        return jsonObj.toString();
    }

    public static String getJsonFileAsString(String filePath){
        return getJsonFileObject(filePath).toString();
    }

}
