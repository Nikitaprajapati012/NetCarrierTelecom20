package com.example.nikita.authentication.activity.presenter;

/*** Created by nikita on 24/8/17.
 */

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
