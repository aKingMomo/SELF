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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.Helper.DBHandler;
import com.project.self.Helper.JSONWriter;
import com.project.self.R;

import org.json.JSONObject;

import com.facebook.FacebookSdk;

import java.util.Arrays;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private EditText userName;
    private EditText passWord;
    private String username;
    private String password;
    private Button login;
    private Button singup;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private User newUser;
    private static final String EMAIL = "email";
    SharedPreferences sharedPreferences;
    JSONObject userJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();

        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        userName =  findViewById(R.id.userName);
        passWord =  findViewById(R.id.passWord);
        login = findViewById(R.id.login);
        singup = findViewById(R.id.signup);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                imLazy();
            }

            @Override
            public void onCancel() {
                // App code
                imLazy();
            }

            @Override
            public void onError(FacebookException exception) {
                imLazy();
            }
        });
        loadPreferences();



    }
    public void imLazy(){
        // App code
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
        Toast.makeText(getApplicationContext(),"Logged In!",Toast.LENGTH_SHORT).show();
        Intent homepage = new Intent(MainActivity.this, HomeScreen.class);
        startActivity(homepage);
    }
    public JSONObject createUserObj(){
        //creates an account for me lol demo purposes >:]
        newUser = new User();

        String tempStr = "Alexis";
        newUser.setFirstName(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        tempStr = "King";
        newUser.setLastName(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        tempStr = "alexis.a.king@pace.edu";
        newUser.setEmail(tempStr);
        Log.d(TAG,"email:"+tempStr);

        tempStr = "AlexisKingFB";
        newUser.setUsername(tempStr);
        Log.d(TAG,"Name:"+tempStr);

        tempStr = "pass123";
        newUser.setPassword(tempStr);

        tempStr = "5168354300";
        newUser.setPhone(tempStr);

        tempStr = "USA";
        newUser.setCountry(tempStr);

        tempStr = "New York";
        newUser.setCity(tempStr);

        tempStr = "New York";
        newUser.setState(tempStr);

        tempStr = "May 10, 1995";
        newUser.setBirthday(tempStr);

        tempStr = "female";
        newUser.setGender(tempStr);

        tempStr = "Lab Data Analyst";
        newUser.setJobTitle(tempStr);


        return newUser.createJson();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
        /*
        This function will check if the preferences are empty
        if yes it will check the JSON on local storage
        if present load from that
        else call to load Json from DB
        */
        //if(sharedPreferences.getString(Consts.userKey,"").equals("")){
        //Log.d(TAG,"Sharedpreferences empty loading JSON");
            //load from DB
        /*
            DBHandler dbHandler =  new DBHandler();
            dbHandler.pullfromDB();
            JSONWriter jsonWriter = new JSONWriter();
            JSONObject userData = jsonWriter.readFromLocalStorage();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Consts.userData,userData.toString());
            editor.commit();

            */
            //read username and password and save it their respective keys
            //then read the user JSON data as string and convert it to JSON and then save each value to a User.java object
        //}


        

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
