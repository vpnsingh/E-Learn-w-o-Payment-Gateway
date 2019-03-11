package com.suvenconsultants.sctple_learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getBaseContext(), MainActivity.class);
                k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                k.putExtra("exit", true);
                startActivity(k);
            }
        });
    }

    public void goHome(View view){
        Intent i = new Intent(getBaseContext(),MainActivity.class);
        startActivity(i);
    }

}
