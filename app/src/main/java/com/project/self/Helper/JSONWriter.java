package com.project.self.Helper;

import android.content.*;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mohitosh on 11/29/2017.
 */

public class JSONWriter {

    JSONObject userData;
    File userFile;

    private String path;

    public void loadJSON(String jsonStr){
        try {
            userData = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void writetoLocalStorage(){
        path = Environment.getExternalStorageDirectory()+"/SelfData/JSONOut";
        userFile = new File(path);
        if(!userFile.exists()){

            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userData);

        try{
            FileOutputStream stream = new FileOutputStream(userFile);
            try {
                stream.write(json.getBytes());
            } finally {
                stream.close();
            }
        }catch (Exception e){

        }
    }
}
