package com.suvenconsultants.sctple_learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);

        Thread t = new Thread() {
            public  void run()
            {
                try {
                    sleep(5*1000);
                    Intent i = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception e) {    }
            }
        };

        t.start();
    }

    protected void onDestroy() {
        super.onDestroy();

    }
}
