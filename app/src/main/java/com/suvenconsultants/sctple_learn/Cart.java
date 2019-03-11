package com.suvenconsultants.sctple_learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    Button b1;
    ListView lv;
    ArrayAdapter<String> arr;
    int TotalAmt,TotlAfterDisc;
    String coursedtl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        b1 = findViewById(R.id.submit);
        lv = findViewById(R.id.list);

        String [] coursename = getResources().getStringArray(R.array.coursename);
        final String [] coursecost = getResources().getStringArray(R.array.coursecost);
        final String [] coursedisc = getResources().getStringArray(R.array.coursedisc);

        TotalAmt = 0;
        TotlAfterDisc=0;

        arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, coursename);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(arr);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = lv.getCheckedItemPositions();
                ArrayList<String> selectedItems = new ArrayList<String>();
                int discAmt =0;
                for (int i = 0; i< checked.size(); i++){
                    int position = checked.keyAt(i);
                    if (checked.valueAt(i))
                        selectedItems.add(arr.getItem(position));
                    TotalAmt = TotalAmt + Integer.parseInt(coursecost[position]);
                    discAmt = Integer.parseInt(coursecost[position]) - ((Integer.parseInt(coursecost[position]) * Integer.parseInt(coursedisc[position]))/100);
                    TotlAfterDisc = TotlAfterDisc + discAmt;

                }

                for (int i = 0; i < selectedItems.size(); i++){
                    coursedtl = coursedtl + selectedItems.get(i);
                    if (i !=(selectedItems.size()-1))
                    {
                        coursedtl = coursedtl + ",";
                    }
                }

                if (coursedtl != "") {

                    File folder = getFilesDir();
                    File mydir = new File(folder, "myfiles"); //Creating an internal dir;
                    if (!mydir.exists())
                    {
                        mydir.mkdir();
                    }

                    String f = "Mydata.txt";
                    File file = new File(mydir, f);
                    FileOutputStream fout = null;
                    try {

                        fout = openFileOutput( f, Context.MODE_PRIVATE);
                        OutputStreamWriter osw = new OutputStreamWriter(fout);
                        osw.write("Couser name : " + coursedtl);
                        osw.write("Total Cost : " + String.valueOf(TotalAmt));
                        osw.write("Total After Discount : " + String.valueOf(TotlAfterDisc));
                        osw.close();
                        Toast.makeText(getBaseContext(),"Congrats,Proceed To Payment",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {e.printStackTrace();}

                    Intent i = new Intent(Cart.this,Checkout.class);
                    i.putExtra("course", coursedtl);
                    i.putExtra("TotalAmt", TotalAmt);
                    i.putExtra("TotlAfterDisc", TotlAfterDisc);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getBaseContext(),"Please Select Atleast One Course", Toast.LENGTH_SHORT).show();
                }
            }
        };
        b1.setOnClickListener(on);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu,menu);
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

}
