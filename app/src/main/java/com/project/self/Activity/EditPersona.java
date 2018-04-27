package com.project.self.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.Helper.FieldCheck;
import com.project.self.Helper.SelectListAdapter;
import com.project.self.R;

import org.json.JSONException;
import org.json.JSONObject;


public class EditPersona extends Activity {

    private static final String TAG = "EditPersona";
    private User newUser;
    private JSONObject userJSON;
    private SharedPreferences sharedPreferences;
    private FieldCheck[] fieldsPreset = new FieldCheck[11];
    private RecyclerView mListView;
    private SelectListAdapter mListAdapter;
    private boolean personaCreated;

    //create will save persona and display it on HomePage
    private Button create;
    //clear will un-check and remove any written changes on the activity screen
    private Button clear;
    private EditText pTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        personaCreated = sharedPreferences.getBoolean("PERSONA-CREATED", false);
        ListView lv = findViewById(R.id.personaListView);
        inflateData();
        String tempStr;
        pTitle = findViewById(R.id.personaTitleEntry);
        pTitle.setText("Default");
        setList();
        findList();
        mListAdapter = new SelectListAdapter(this,fieldsPreset,sharedPreferences);
        lv.setAdapter(mListAdapter);

        create = findViewById(R.id.createPersona);
        if(personaCreated)
            create.setText("SAVE");
        clear = findViewById(R.id.clearPersona);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();
                Toast.makeText(getApplicationContext(), "PERSONA DETAILS UPDATED", Toast.LENGTH_SHORT).show();
                Intent homepage = new Intent(EditPersona.this, HomeScreen.class);
                startActivity(homepage);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearList();
                mListAdapter.notifyDataSetChanged();
            }
        });

    }

    public void setList(){


        fieldsPreset[0]= new FieldCheck(Consts.fName,false);
        fieldsPreset[1]= new FieldCheck(Consts.lName,false);
        fieldsPreset[2]= new FieldCheck(Consts.birth,false);
        fieldsPreset[3]= new FieldCheck(Consts.city,false);
        fieldsPreset[4]= new FieldCheck(Consts.state,false);
        fieldsPreset[5]= new FieldCheck(Consts.country,false);
        fieldsPreset[6]= new FieldCheck(Consts.jTitle,false);
        fieldsPreset[7]= new FieldCheck(Consts.gender,false);
        fieldsPreset[8]= new FieldCheck(Consts.phone,false);
        fieldsPreset[9]= new FieldCheck(Consts.eMail,false);
        fieldsPreset[10]= new FieldCheck("Default", true);

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

    public void updateList(){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String tempStr = pTitle.getText().toString();
        editor.putString("PERSONA-TITLE",tempStr);
        editor.putBoolean("PERSONA-CREATED", true);
        editor.commit();
    }
    public void findList(){

        if(personaCreated){
            //Toast.makeText(getApplicationContext(), "FOUND PERSONA DETAILS", Toast.LENGTH_SHORT).show();
            String personaField="";
            for(int i=0;i<11;i++){
                personaField="PERSONA-"+fieldsPreset[i].getName();
                fieldsPreset[i] = new FieldCheck(fieldsPreset[i].getName(), sharedPreferences.getBoolean(personaField,false));
            }
        }
    }
    public void clearList(){
        setList();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("PERSONA-CREATED", false);
        String personaField="";
        for(int i=0;i<11;i++){
            personaField="PERSONA-"+fieldsPreset[i].getName();
            editor.putBoolean(personaField, fieldsPreset[i].getEnabled());
        }
        editor.commit();
    }

}
