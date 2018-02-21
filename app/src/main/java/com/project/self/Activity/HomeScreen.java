package com.project.self.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.project.self.DataClass.Consts;
import com.project.self.DataClass.User;
import com.project.self.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    User newUser;
    private final static String TAG = "HomeScreen";

    TextView name;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inflateData();


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    //loads data from shareprefernces and updates it on screen
    public void inflateData(){
        JSONObject userObj;
        SharedPreferences sharedPreferences =getSharedPreferences("Admin",MODE_PRIVATE);
        String userdata = sharedPreferences.getString(Consts.userData,"");
        newUser = new User();
        Log.d("HOMESCREEN","inflateData"+userdata);
        if(!userdata.equals(null)) {
            try {


                userObj = new JSONObject(userdata);
                newUser.setFirstName(userObj.getString(Consts.fName));
                newUser.setLastName(userObj.getString(Consts.lName));
                newUser.setBirthday(userObj.getString(Consts.birth));
                newUser.setCity(userObj.getString(Consts.city));
                newUser.setJobTitle(userObj.getString(Consts.jTitle));
                newUser.setState(userObj.getString(Consts.state));
                newUser.setGender(userObj.getString(Consts.gender));
                newUser.setPhone(userObj.getString(Consts.phone));
                newUser.setEmail(userObj.getString(Consts.eMail));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        /*else {
            //super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }

        */
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profileEdit) {
            // hand profile edit
            Intent intent = new Intent(HomeScreen.this, ModifyProfile.class);
            startActivity(intent);
        } else if (id == R.id.personaEdit) {
            //handle persona edit
            Intent intent = new Intent(HomeScreen.this, Persona.class);
            startActivity(intent);

        } else if (id == R.id.sharepersona) {
            //handle share persona
        } else if (id == R.id.shareapp) {
            //handle app sharing
        } else if (id == R.id.setting) {
            //app settings
        } else if (id == R.id.logout) {
            //logout
            SharedPreferences sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d(TAG, "Auto-Login OFF");
            editor.putBoolean(Consts.loginKey, false);
            editor.apply();
            Intent homepage = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(homepage);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
