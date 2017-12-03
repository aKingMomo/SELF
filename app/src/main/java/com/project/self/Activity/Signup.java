package com.project.self.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create userObject
                Log.d(TAG,"Saving User Data");
                userJSON = createUserObj();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Consts.userData,userJSON.toString());
                editor.commit();
                Log.d(TAG,"Saving UserJSON Data");
                //reset auto-login to false if new account is created
                Log.d(TAG,"Auto-Login OFF");
                editor.putBoolean(Consts.loginKey,false);
                editor.apply();

                //send userdata to next activity
                Toast.makeText(getApplicationContext(),"NEW ACCOUNT CREATED",Toast.LENGTH_SHORT).show();
                Intent homepage = new Intent(Signup.this, HomeScreen.class);
                startActivity(homepage);
            }
        });

    }
    public JSONObject createUserObj(){

        newUser = new User();
        newUser.setFirstName(((EditText)findViewById(R.id.fname)).getText().toString());
        newUser.setLastName(((EditText)findViewById(R.id.lname)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.email)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.phone)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.country)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.city)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.state)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.birthday)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.gender)).getText().toString());
        newUser.setFirstName(((EditText)findViewById(R.id.jtitle)).getText().toString());

        return newUser.createJson();
    }
}
