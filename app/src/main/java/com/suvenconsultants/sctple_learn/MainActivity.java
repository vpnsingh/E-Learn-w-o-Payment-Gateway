package com.suvenconsultants.sctple_learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView cv1,cv2,cv3,cv4,cv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       cv1 = (CardView) findViewById(R.id.android);
       cv2 = (CardView) findViewById(R.id.web);
       cv3 = (CardView) findViewById(R.id.java);
       cv4 = (CardView) findViewById(R.id.db);
       cv5 = (CardView) findViewById(R.id.about);

        if(getIntent().getBooleanExtra("exit",false))
        {
            finish();
        }


    }
    public void goforweb(View v)
    {
        Intent i1 = new Intent(getBaseContext(), WebDev.class);
        startActivity(i1);
    }
    public void goforandroid(View v)
    {
        Intent i2 = new Intent(getBaseContext(), Androids.class);
        startActivity(i2);
    }
    public void goforjava(View v)
    {
        Intent i3 = new Intent(getBaseContext(), Java.class);
        startActivity(i3);
    }
    public void gofordb(View v)
    {
        Intent i4 = new Intent(getBaseContext(), Database.class);
        startActivity(i4);
    }
    public void goformore(View v)
    {
        Intent i4 = new Intent(getBaseContext(), More.class);
        startActivity(i4);
    }
}

