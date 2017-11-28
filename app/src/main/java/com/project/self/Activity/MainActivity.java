package com.project.self.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.self.R;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private EditText userName;
    private EditText passWord;
    private Button login;
    private Button singup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName =  findViewById(R.id.userName);
        passWord =  findViewById(R.id.passWord);
        login = findViewById(R.id.login);
        singup = findViewById(R.id.signup);
    }

    public void onClickListener(final View view){
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(view.getId()==login.getId()){
                    Log.d(TAG,"onClick Called for Login");
                    //todo validate
                    //1. Username and password are not empty
                    //2. Match with SharedPreferences
                    //if true have autologin set as true so this page doesn't load next time

                    //upon success redirect to homepage
                    Intent homepage = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(homepage);
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
    }
}
