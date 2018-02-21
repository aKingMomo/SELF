package com.project.self.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.Helper.FieldCheck;
import com.project.self.Helper.SelectListAdapter;
import com.project.self.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Persona extends Activity {

    private static final String TAG = "Persona";
    private User newUser;
    private JSONObject userJSON;
    private SharedPreferences sharedPreferences;
    private FieldCheck[] fieldsPreset = new FieldCheck[10];
    private RecyclerView mListView;
    private SelectListAdapter mListAdapter;

    //create will save persona and display it on HomePage
    private Button create;
    //clear will un-check and remove any written changes on the activity screen
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        ListView lv = findViewById(R.id.personaListView);
        inflateData();
        setList();
        mListAdapter = new SelectListAdapter(this,fieldsPreset);
        lv.setAdapter(mListAdapter);

        create = findViewById(R.id.createPersona);
        clear = findViewById(R.id.clearPersona);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setList();
                mListAdapter.notifyDataSetChanged();
            }
        });

    }

    public void setList(){


        fieldsPreset[0]= new FieldCheck(Consts.fName,false);
        fieldsPreset[1]= new FieldCheck(Consts.lName,false);
        fieldsPreset[2]= new FieldCheck(Consts.birth,false);
        fieldsPreset[3]= new FieldCheck(Consts.country,false);
        fieldsPreset[4]= new FieldCheck(Consts.city,false);
        fieldsPreset[5]= new FieldCheck(Consts.jTitle,false);
        fieldsPreset[6]= new FieldCheck(Consts.state,false);
        fieldsPreset[7]= new FieldCheck(Consts.gender,false);
        fieldsPreset[8]= new FieldCheck(Consts.phone,false);
        fieldsPreset[9]= new FieldCheck(Consts.eMail,false);

    }


    public void inflateData(){
        String userdata = sharedPreferences.getString(Consts.userData,"");
        newUser = new User();
        Log.d(TAG,"inflateData"+userdata);
        if(!userdata.equals(null)) {
            try {


                userJSON = new JSONObject(userdata);
                newUser.setFirstName(userJSON.getString(Consts.fName));
                newUser.setLastName(userJSON.getString(Consts.lName));
                newUser.setBirthday(userJSON.getString(Consts.birth));
                newUser.setCountry(userJSON.getString(Consts.country));
                newUser.setCity(userJSON.getString(Consts.city));
                newUser.setJobTitle(userJSON.getString(Consts.jTitle));
                newUser.setState(userJSON.getString(Consts.state));
                newUser.setGender(userJSON.getString(Consts.gender));
                newUser.setPhone(userJSON.getString(Consts.phone));
                newUser.setEmail(userJSON.getString(Consts.eMail));
                newUser.setUsername(userJSON.getString(Consts.userKey));
                newUser.setPassword(userJSON.getString(Consts.passKey));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
