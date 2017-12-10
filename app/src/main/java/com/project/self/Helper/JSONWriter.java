package com.project.self.Helper;

import android.content.*;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.self.DataClass.Consts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
        path = Consts.folderLocation;
        File outputFolder = new File(path);

        if(!outputFolder.exists()){
            Log.d("JSONWriter","Folder Create:"+outputFolder.getPath()+outputFolder.mkdirs());

        }

        userFile = new File(outputFolder+"/JSONOut.json");
            try {
                Log.d("JSONWriter","creating"+userFile.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userData);

        try{
            FileOutputStream stream = new FileOutputStream(userFile);
            try {
                stream.write(json.getBytes());
                Log.d("JSONWriter","written to local storage");
            } finally {
                stream.close();
            }
        }catch (Exception e){

        }
    }
    public JSONObject readFromLocalStorage(){
        File folder = new File(Consts.folderLocation);
        if (folder.exists()){
            userFile = new File(folder+"/JSONOut.json");
            Gson gson = new Gson();

            try {


                //Read the employee.json file
                BufferedReader br = new BufferedReader(
                        new FileReader(userFile));
                //convert the json to  JSONObject
                userData = gson.fromJson(br,JSONObject.class);
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }
        return userData;

    }
}
