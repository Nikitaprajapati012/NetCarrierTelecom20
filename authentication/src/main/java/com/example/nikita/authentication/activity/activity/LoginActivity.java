package com.example.nikita.authentication.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecosmob.wsmodule.WSResponse;
import com.ecosmob.wsmodule.WebService;
import com.example.nikita.authentication.R;
import com.example.nikita.authentication.activity.presenter.LoginPresenter;
import com.example.nikita.authentication.activity.presenter.LoginPresenterImpl;
import com.example.nikita.authentication.activity.utils.Constants;
import com.example.nikita.authentication.activity.utils.Utils;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, WSResponse {
    public EditText edEmail, edPassword;
    public TextView txtSubmit;
    public String strEmail, strPassword;
    public LoginPresenter presenter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        findViewId();
        init();
    }

    // TODO: 24/8/17 initilization
    private void init() {
        presenter = new LoginPresenterImpl(LoginActivity.this, this);
        txtSubmit.setOnClickListener(this);
    }

    // TODO: 24/8/17 bind the widget
    private void findViewId() {
        edEmail = (EditText) findViewById(R.id.activity_login_edemail);
        edPassword = (EditText) findViewById(R.id.activity_login_edpassword);
        txtSubmit = (TextView) findViewById(R.id.activity_login_txtsubmit);
        progressBar = (ProgressBar) findViewById(R.id.activity_login_progressbar);
    }

    public void onShowToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    public void userMakeLogin() {
        // TODO: 4/9/17 pass the header part here...
        WebService.API_URL = Constants.BASE_URL;
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("device", "IOS");
        headerMap.put("token", "2ORsgkSvsz0xvzxDDgRFN9HjehlwCgUd");
        headerMap.put("version", "1.1");
        headerMap.put("userId", "18942");
        headerMap.put("lang", "en");

        // TODO: 4/9/17 pass the body part here...
        JsonObject bodyMap = new JsonObject();
        bodyMap.addProperty("acc_holder_name", "TST USR");
        bodyMap.addProperty("acc_no", "1234567891234533");
        bodyMap.addProperty("acc_type", "SAVING");
        bodyMap.addProperty("ifsc_code", "ADSD0343433");
        bodyMap.addProperty("name", "Bank Of Baroda");
        bodyMap.addProperty("is_primary", "1");

        // TODO: 4/9/17 call the Api here
        new WebService().callPostMethod(Constants.addBank, headerMap, bodyMap, this);
    }

    public void setUsernameError() {
        edEmail.setError(getApplicationContext().getResources().getString(R.string.emailempty));
    }

    public void setWrongUsernameError() {
        edEmail.setError(getApplicationContext().getResources().getString(R.string.invalidemail));
    }

    public void setPasswordError() {
        edPassword.setError(getApplicationContext().getResources().getString(R.string.pwdempty));
    }

    @Override
    public void onClick(View v) {
        strEmail = edEmail.getText().toString().trim();
        strPassword = edPassword.getText().toString().trim();
        presenter.validateCredentials(strEmail, strPassword);
    }

    @Override
    public void onSuccess(String response, int code) {
        if (response != null) {
            JSONObject data = null;
            try {
                data = new JSONObject(response);
                if (data != null) {
                    Log.d("onResponse: ", "data - " + data);
                    String email = "nikita@gmail.com";
                    String userId = "1";
                    Utils.WriteSharedPreference(LoginActivity.this, Constants.USER_EMAILID, email);
                    Utils.WriteSharedPreference(LoginActivity.this, Constants.USER_ID, userId);
                    onShowToast(this.getResources().getString(R.string.loginsucess));
                    Intent intent = new Intent(LoginActivity.this, Class.forName("com.ecosmob.netcarriertelecom.MainActivity"));
                    startActivity(intent);
                } else {
                    onShowToast("Data Not Found");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(String error) {
        onShowToast(error);
    }
}