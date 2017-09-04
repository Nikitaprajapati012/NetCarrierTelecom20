package com.ecosmob.netcarriertelecom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.nikita.authentication.activity.activity.LoginActivity;
import com.example.nikita.authentication.activity.utils.Constants;
import com.example.nikita.authentication.activity.utils.Utils;

public class MainActivity extends AppCompatActivity {
    LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = Utils.ReadSharedPreference(MainActivity.this, Constants.USER_ID);
        if (userId.equalsIgnoreCase("")) {
            Intent iLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(iLogin);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);

            TextView textView = (TextView) findViewById(R.id.activity_main_txt);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = null;
                    try {
                        intent = new Intent(MainActivity.this,
                                Class.forName("com.example.nikita.authentication.activity.activity.LoginActivity"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
