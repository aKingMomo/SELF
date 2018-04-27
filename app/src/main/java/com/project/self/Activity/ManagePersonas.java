package com.project.self.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.project.self.R;

public class ManagePersonas extends AppCompatActivity {

    private CardView card;
    private TextView personaTitle;
    private boolean personaCreated;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_personas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        card = (CardView)findViewById(R.id.personaCard);
        personaTitle = (TextView)findViewById(R.id.personaTitle);
        sharedPreferences = getSharedPreferences("Admin",MODE_PRIVATE);
        personaCreated = sharedPreferences.getBoolean("PERSONA-CREATED", false);

        if(personaCreated){
            personaTitle.setText(sharedPreferences.getString("PERSONA-TITLE","Default"));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagePersonas.this, EditPersona.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
