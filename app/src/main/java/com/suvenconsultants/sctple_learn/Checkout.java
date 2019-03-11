package com.suvenconsultants.sctple_learn;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {
    TextView tvCourse, tvTotlAmt, tvTotlDiscAmt;
    private EditText inputcard, inputexpire, inputcvv;
    private TextInputLayout inputLayoutcard, inputLayoutexpire, inputLayoutcvv;
    int TotlAfterDisc;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        inputLayoutcard = (TextInputLayout) findViewById(R.id.input_layout_card);
        inputLayoutexpire = (TextInputLayout) findViewById(R.id.input_layout_expire);
        inputLayoutcvv = (TextInputLayout) findViewById(R.id.input_layout_cvv);
        inputcard = (EditText) findViewById(R.id.input_card);
        inputexpire = (EditText) findViewById(R.id.input_expire);
        inputcvv = (EditText) findViewById(R.id.input_cvv);

        inputcard.addTextChangedListener(new MyTextWatcher(inputcard));
        inputexpire.addTextChangedListener(new MyTextWatcher(inputexpire));
        inputcvv.addTextChangedListener(new MyTextWatcher(inputcvv));


        tvCourse = findViewById(R.id.tvCourse);
        tvTotlAmt = findViewById(R.id.tvTotlAmt);
        tvTotlDiscAmt = findViewById(R.id.tvTotlDiscAmt);


        int TotalAmt = getIntent().getIntExtra("TotalAmt", 0);
        TotlAfterDisc = getIntent().getIntExtra("TotlAfterDisc", 0);
        course = getIntent().getStringExtra("course");
        tvCourse.setText(course);
        tvTotlAmt.setText(String.valueOf(TotalAmt));
        tvTotlDiscAmt.setText(String.valueOf(TotlAfterDisc));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkmenu,menu);
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


    public void Click(View v) {
        submitForm();
    }

    private void submitForm() {
        if (!validateCard()) {
            return;
        }

        if (!validateExpire()) {
            return;
        }

        if (!validateCvv()) {
            return;
        }
        Intent i = new Intent(getBaseContext(), LastActivity.class);
        i.putExtra("CouserName", course);
        i.putExtra("TotalAmt", TotlAfterDisc);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateCard() {
        if (inputcard.getText().toString().trim().isEmpty()) {
            inputLayoutcard.setError(getString(R.string.err_msg_name));
            requestFocus(inputcard);
            return false;
        } else {
            inputLayoutcard.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateExpire() {
        if (inputexpire.getText().toString().trim().isEmpty()) {
            inputLayoutexpire.setError(getString(R.string.err_msg_name));
            requestFocus(inputexpire);
            return false;
        } else {
            inputLayoutexpire.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCvv() {
        if (inputcvv.getText().toString().trim().isEmpty()) {
            inputLayoutcvv.setError(getString(R.string.err_msg_name));
            requestFocus(inputcvv);
            return false;
        } else {
            inputLayoutcvv.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_card:
                    validateCard();
                    break;
                case R.id.input_expire:
                    validateExpire();
                    break;
                case R.id.input_cvv:
                    validateCvv();
                    break;
            }
        }

    }
}
