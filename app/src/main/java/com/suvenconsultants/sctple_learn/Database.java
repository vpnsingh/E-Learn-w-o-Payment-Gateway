package com.suvenconsultants.sctple_learn;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Database extends AppCompatActivity {
    RelativeLayout notificationCount1;
    TextView tvobj, tvcont, tvcost, tvdisc;
    int TotalAmt,TotlAfterDisc;
    String coursedtl;
    static int i = 3;
    File imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_121_24);
        setSupportActionBar(toolbar);

        notificationCount1 = (RelativeLayout) findViewById(R.id.relative_layout1);
        tvobj = findViewById(R.id.tvobj);
        tvcont = findViewById(R.id.tvcont);
        tvcost = findViewById(R.id.tvcost);
        tvdisc = findViewById(R.id.tvdisc);


        String [] coursename = getResources().getStringArray(R.array.coursename);
        String [] courseobj = getResources().getStringArray(R.array.courseobj);
        String [] coursecont = getResources().getStringArray(R.array.coursecont);
        String [] coursecost = getResources().getStringArray(R.array.coursecost);
        String [] coursedisc = getResources().getStringArray(R.array.coursedisc);

        tvobj.setText(courseobj[i]);
        tvcont.setText(coursecont[i]);
        tvcost.setText(String.valueOf(coursecost[i]) + "/-");
        tvdisc.setText(String.valueOf(coursedisc[i]) + "%");

        coursedtl = coursename[i];
        TotalAmt = TotalAmt + Integer.parseInt(coursecost[i]);
        TotlAfterDisc = Integer.parseInt(coursecost[i]) - ((Integer.parseInt(coursecost[i]) * Integer.parseInt(coursedisc[i]))/100);

           }

    public void buyNow(View view){
        Intent bn = new Intent(getBaseContext(),Checkout.class);
        bn.putExtra("course", coursedtl);
        bn.putExtra("TotalAmt", TotalAmt);
        bn.putExtra("TotlAfterDisc", TotlAfterDisc);
        startActivity(bn);
    }
    public void addCart(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent ac = new Intent(getBaseContext(),Cart.class);
                        startActivity(ac);
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.allmenu,menu);
        MenuItem item1 = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item1, R.layout.notification_badge_count);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent k;
        switch (item.getItemId()) {
            case R.id.share:
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
                return true;

            case R.id.mainmenu:
                k = new Intent(getBaseContext(), MainActivity.class);
                startActivity(k);
                finish();
                return true;

            case R.id.checkout:
                k = new Intent(getBaseContext(), Checkout.class);
                k.putExtra("course", coursedtl);
                k.putExtra("TotalAmt", TotalAmt);
                k.putExtra("TotlAfterDisc", TotlAfterDisc);
                startActivity(k);
                finish();
                return true;

            case R.id.addCart:
                k = new Intent(getBaseContext(), Cart.class);
                startActivity(k);
                finish();
                return true;

            case R.id.contact:
                k = new Intent(getBaseContext(), More.class);
                startActivity(k);
                finish();
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



    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Database Development Certification at SCTPL";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Database@SCTPL");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}

