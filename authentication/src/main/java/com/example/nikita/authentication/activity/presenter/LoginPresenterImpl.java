package com.example.nikita.authentication.activity.presenter;

import android.content.Context;

import com.example.nikita.authentication.activity.activity.LoginActivity;
import com.example.nikita.authentication.activity.iteractor.LoginInteractor;
import com.example.nikita.authentication.activity.iteractor.LoginInteractorImpl;

/*** Created by nikita on 24/8/17.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {
    private LoginActivity loginView;
    private LoginInteractor loginInteractor;
    private Context mContext;

    public LoginPresenterImpl(Context context, LoginActivity loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.mContext = context;
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginInteractor.login(mContext, username, password, this);
    }


    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
        }
    }

    @Override
    public void onWrongUsernameError() {
        if (loginView != null) {
            loginView.setWrongUsernameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.userMakeLogin();
        }
    }
}
