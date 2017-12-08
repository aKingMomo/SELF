package com.project.self.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ModifyProfile extends Activity {

    private static final String TAG = "ModifyProfile";
    User newUser;
    JSONObject userJSON;
    SharedPreferences sharedPreferences;
    private Button modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);
        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        inflateData();
        setDataFields();

        modify = findViewById(R.id.modify);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

    }

    //update the newUser Object and call create Json to update userJSON
    //and then update sharedpreferences
    public void updateData(){
        //TODO listed above
    }

    //read data already save in SharedPreference
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

    //set read data from newUser to EditText in Layout
    public void setDataFields(){
        //TODO add EditText to corresponding newUser Functions

        String tempStr;
        EditText fname = findViewById(R.id.fname);
        tempStr = newUser.getFirstName();
        Log.d(TAG,"FName:"+tempStr);
        fname.setText(tempStr);

        EditText lname = findViewById(R.id.lname);
        tempStr = newUser.getLastName();
        Log.d(TAG,"LName:"+tempStr);
        lname.setText(tempStr);

        EditText email = findViewById(R.id.email);
        tempStr = newUser.getEmail();
        Log.d(TAG,"Email:"+tempStr);
        email.setText(tempStr);

        EditText username = findViewById(R.id.uNameCreate);
        tempStr = newUser.getUsername();
        Log.d(TAG,"UserName:"+tempStr);
        username.setText(tempStr);

        EditText password = findViewById(R.id.passCreate);
        tempStr = newUser.getPassword();
        Log.d(TAG,"Password:"+tempStr);
        password.setText(tempStr);

        EditText cPassword = findViewById(R.id.cPassword);
        tempStr = newUser.getPassword();
        Log.d(TAG,"Confirm Password:"+tempStr);
        cPassword.setText(tempStr);

        EditText phone = findViewById(R.id.phone);
        tempStr = newUser.getPhone();
        Log.d(TAG,"Phone:"+tempStr);
        phone.setText(tempStr);

        EditText country = findViewById(R.id.country);
        tempStr = newUser.getCountry();
        Log.d(TAG,"Country:"+tempStr);
        country.setText(tempStr);

        EditText city = findViewById(R.id.city);
        tempStr = newUser.getCity();
        Log.d(TAG,"City:"+tempStr);
        city.setText(tempStr);

        EditText state = findViewById(R.id.state);
        tempStr = newUser.getState();
        Log.d(TAG,"State:"+tempStr);
        state.setText(tempStr);

        EditText birth = findViewById(R.id.birthday);
        tempStr = newUser.getBirthday();
        Log.d(TAG,"Birth:"+tempStr);
        birth.setText(tempStr);

        EditText gender = findViewById(R.id.gender);
        tempStr = newUser.getGender();
        Log.d(TAG,"Sex:"+tempStr);
        gender.setText(tempStr);

        EditText jtitle = findViewById(R.id.jtitle);
        tempStr = newUser.getJobTitle();
        Log.d(TAG,"Title:"+tempStr);
        jtitle.setText(tempStr);
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

        EditText phone = findViewById(R.id.phone);
        tempStr = phone.getText().toString();
        newUser.setFirstName(tempStr);

        EditText country = findViewById(R.id.country);
        tempStr = country.getText().toString();
        newUser.setCountry(tempStr);

        EditText city = findViewById(R.id.city);
        tempStr = city.getText().toString();
        newUser.setCity(tempStr);

        EditText state = findViewById(R.id.state);
        tempStr = state.getText().toString();
        newUser.setState(tempStr);

        EditText birth = findViewById(R.id.birthday);
        tempStr = birth.getText().toString();
        newUser.setBirthday(tempStr);

        EditText gender = findViewById(R.id.gender);
        tempStr = gender.getText().toString();
        newUser.setGender(tempStr);

        EditText jtitle = findViewById(R.id.jtitle);
        tempStr = jtitle.getText().toString();
        newUser.setJobTitle(tempStr);


        return newUser.createJson();
    }
}
