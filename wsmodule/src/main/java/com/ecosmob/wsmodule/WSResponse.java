package com.ecosmob.wsmodule;

/**
 * Created by hardik on 27/1/17.
 */

public interface WSResponse {

    void onSuccess(String response, int code);

    void onFailure(String error);
}
