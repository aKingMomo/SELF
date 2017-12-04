package com.project.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.R;

import org.json.JSONObject;

public class Signup extends Activity {

    private static final String TAG = "Signup";
    private Button create;
    User newUser;
    JSONObject userJSON;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create userObject
                Log.d(TAG,"Saving User Data");
                userJSON = createUserObj();
                sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Consts.userData, userJSON.toString());
                    editor.putString(Consts.userKey,newUser.getUsername());
                    editor.putString(Consts.passKey,newUser.getPassword());
                    editor.commit();
                    Log.d(TAG, "Saving UserJSON Data"+sharedPreferences.getString(Consts.userData,""));
                    //reset auto-login to false if new account is created
                    Log.d(TAG, "Auto-Login OFF");
                    editor.putBoolean(Consts.loginKey, false);
                    editor.apply();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //send userdata to next activity
                Toast.makeText(getApplicationContext(),"NEW ACCOUNT CREATED",Toast.LENGTH_SHORT).show();
                Intent homepage = new Intent(Signup.this, HomeScreen.class);
                startActivity(homepage);
            }
        });

    }
    public JSONObject createUserObj(){

        newUser = new User();

        EditText fname = findViewById(R.id.fname);
        String tempStr = fname.getText().toString();
        newUser.setFirstName(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        EditText lname = findViewById(R.id.lname);
        tempStr = lname.getText().toString();
        newUser.setLastName(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        EditText email = findViewById(R.id.email);
        tempStr = email.getText().toString();
        newUser.setEmail(tempStr);
        Log.d(TAG,"email:"+tempStr);

        EditText username = findViewById(R.id.uNameCreate);
        tempStr = username.getText().toString();
        newUser.setUsername(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        EditText password = findViewById(R.id.passCreate);
        tempStr = password.getText().toString();
        newUser.setPassword(tempStr);
        /*
        EditText phone = findViewById(R.id.phone);
        tempStr = phone.getText().toString();
        newUser.setFirstName(tempStr);

        EditText country = findViewById(R.id.country);
        tempStr = country.getText().toString();
        newUser.setFirstName(tempStr);

        EditText city = findViewById(R.id.city);
        tempStr = city.getText().toString();
        newUser.setFirstName(tempStr);

        EditText state = findViewById(R.id.state);
        tempStr = state.getText().toString();
        newUser.setFirstName(tempStr);

        EditText birth = findViewById(R.id.birthday);
        tempStr = birth.getText().toString();
        newUser.setFirstName(tempStr);

        EditText gender = findViewById(R.id.gender);
        tempStr = gender.getText().toString();
        newUser.setFirstName(tempStr);

        EditText jtitle = findViewById(R.id.jtitle);
        tempStr = jtitle.getText().toString();
        newUser.setFirstName(tempStr);
        */

        return newUser.createJson();
    }
}
