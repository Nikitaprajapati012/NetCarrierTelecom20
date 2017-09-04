package com.example.nikita.authentication.activity.iteractor;

import android.content.Context;

/*** Created by nikita on 24/8/17.
 */

public interface LoginInteractor {
    void login(Context context, String username, String password, OnLoginFinishedListener listener);

    interface OnLoginFinishedListener {

        void onUsernameError();

        void onWrongUsernameError();

        void onPasswordError();

        void onSuccess();
    }
}
