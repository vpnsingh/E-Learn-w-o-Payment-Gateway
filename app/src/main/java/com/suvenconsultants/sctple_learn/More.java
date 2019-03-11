package com.suvenconsultants.sctple_learn;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class More extends AppCompatActivity {
    private List<Course> courseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_121_24);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CourseAdapter(courseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        prepareCourseData();

    }

    private void prepareCourseData() {
        Course course = new Course("Angular4 with Bootstrap", "Fees: 3950", "15% Off");
        courseList.add(course);
        course = new Course("Python with ML", "Fees: 4950", "10% Off");
        courseList.add(course);
        course = new Course("Data Analytics using R", "Fees: 4550", "20% Off");
        courseList.add(course);
        course = new Course("Nodejs with Mongodb", "Fees: 5500", "10% Off");
        courseList.add(course);




        mAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.othermenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Intent k;
        switch (item.getItemId()) {
            case R.id.mainmenu:
                k = new Intent(getBaseContext(), MainActivity.class);
                startActivity(k);
                finish();
                return true;

            case R.id.dev:
                Toast.makeText(getBaseContext(),"Developed by Vipin Singh",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.exit:
                k = new Intent(getBaseContext(), MainActivity.class);
                k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                k.putExtra("exit", true);
                startActivity(k);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doWhatsapp(View view){
       /* startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(
                        "https://api.whatsapp.com/send?phone=+919870014450&text=Hii%20SCTPL%20Please%20Send%20Me%20Course%20Details"
                )));*/
        String smsNumber="+919870014450";
        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

        startActivity(sendIntent);

    }

    public void doGmail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","rockyjagtiani@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Course Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "<!-- Put Your Query-->");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void doCall(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
        callIntent.setData(Uri.parse("tel:+919870014450"));    //this is the phone number calling
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        }else {     //have got permission
            try{
                startActivity(callIntent);  //call activity and make phone call
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void doWeb(View view){
        String url = "http://www.suvenconsultants.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
