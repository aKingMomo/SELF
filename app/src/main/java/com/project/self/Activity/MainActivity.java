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
import com.project.self.R;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private EditText userName;
    private EditText passWord;
    private String username;
    private String password;
    private Button login;
    private Button singup;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName =  findViewById(R.id.userName);
        passWord =  findViewById(R.id.passWord);
        login = findViewById(R.id.login);
        singup = findViewById(R.id.signup);
        loadPreferences();



    }

    public void autoLogin(){
        Log.d(TAG,"Auto-Login Checking");

        //if user if already logged in then redirect
        if(sharedPreferences.getBoolean(Consts.loginKey,false)){
        Log.d(TAG,"Auto-Login Check, successful");
            //redirect to homePage
            Intent homepage = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(homepage);
        }

    }
    public void loadPreferences(){
        Log.d(TAG,"Preferences are Loading");
        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        /*
    This function will check if the preferences are empty
    if yes it will check the JSON on local storage
    if present load from that
    else call to load Json from DB

        if(sharedPreferences.getString(Consts.userKey,"").equals("")){
        Log.d(TAG,"Sharedpreferences empty loading JSON");
            //load from DB
            //read username and password and save it their respective keys
            //then read the user JSON data as string and convert it to JSON and then save each value to a User.java object
        }

        */


    }

    public void onClickListener(final View view){
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(view.getId()==login.getId()) {
                    Log.d(TAG, "onClick Called for Login");
                    //todo validate
                    //1. Username and password are not empty
                    //2. Match with Sharedpreferences
                    username = userName.getText().toString();
                    password = passWord.getText().toString();

                    if (username.equals(null) || password.equals(null)) {
                        Toast.makeText(getApplicationContext(), R.string.fieldmissing, Toast.LENGTH_SHORT).show();
                    }

                    //validate with sharedpreference
                    Log.d(TAG,"Username"+sharedPreferences.getString(Consts.userKey,"")+"Password:"+sharedPreferences.getString(Consts.passKey,""));
                    if (sharedPreferences.getString(Consts.userKey, "").equals(username)) {
                        if (sharedPreferences.getString(Consts.passKey, "").equals(password)) {

                            Log.d(TAG,"Login Successful saving auto-login true");
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Consts.loginKey,true);
                            editor.commit();
                            Intent homepage = new Intent(MainActivity.this, HomeScreen.class);
                            startActivity(homepage);
                        }
                        else {
                        Toast.makeText(getApplicationContext(), R.string.wrongpassword, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        Toast.makeText(getApplicationContext(),R.string.wrongusername,Toast.LENGTH_SHORT).show();
                    }


                }
                if(view.getId()==singup.getId()){
                    Log.d(TAG,"onClick Called for SignUp");
                    Intent signuppage = new Intent(MainActivity.this, Signup.class);
                    startActivity(signuppage);
                }

            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();

        //some stuff that need to be done on resume of this screen
        autoLogin();
    }
}
