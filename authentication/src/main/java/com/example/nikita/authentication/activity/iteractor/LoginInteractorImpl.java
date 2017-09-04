package com.example.nikita.authentication.activity.iteractor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ecosmob.wsmodule.WSResponse;

import org.json.JSONException;
import org.json.JSONObject;

/*** Created by nikita on 24/8/17. */

public class LoginInteractorImpl implements LoginInteractor, WSResponse {
    private Context mContext;

    @Override
    public void login(Context context, String username,
                      String password,
                      OnLoginFinishedListener listener) {

        this.mContext = context;
        boolean statusValidation = false;

        // TODO: 25/8/17 validate the username/email
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                listener.onWrongUsernameError();
                statusValidation = true;
            }
        }

        // TODO: 25/8/17 validate the password
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            statusValidation = true;
        }

        // TODO: 25/8/17 sucessfully login
        if (!statusValidation) {
            listener.onSuccess();
        }
    }

    @Override
    public void onSuccess(String response, int code) {
        if (response != null) {
            JSONObject data = null;
            try {
                data = new JSONObject(response);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("onResponse: ", "data - " + data);
        } else {
            Toast.makeText(mContext, "Data Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(mContext, "" + error, Toast.LENGTH_SHORT).show();

    }
}
