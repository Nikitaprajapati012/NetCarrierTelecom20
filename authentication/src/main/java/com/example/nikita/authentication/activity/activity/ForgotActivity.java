package com.example.nikita.authentication.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikita.authentication.R;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText edEmail;
    public TextView txtSubmit;
    public String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        findById();
        click();
    }

    // TODO: 25/8/17 perform the click event
    private void click() {
        txtSubmit.setOnClickListener(this);
    }

    // TODO: 25/8/17 widget bind by id
    private void findById() {
        edEmail = (EditText) findViewById(R.id.activity_forgot_edemail);
        txtSubmit = (TextView) findViewById(R.id.activity_forgot_txtsubmit);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_forgot_txtsubmit) {
            strEmail = edEmail.getText().toString().trim();
            verifyEmail(strEmail);

        }
    }

    // TODO: 25/8/17 vertify the email for password
    private void verifyEmail(String strEmail) {
        Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
