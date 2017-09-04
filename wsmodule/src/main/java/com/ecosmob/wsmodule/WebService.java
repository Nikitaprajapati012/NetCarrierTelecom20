package com.ecosmob.wsmodule;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*** Created by sagar on 01/09/17.
 */

public class WebService {

    public static String API_URL = "";

    WSResponse objWsResponse;

    // : mathod parameters
    // @URL : Mendatory
    // @parameters : can be nil
    // @headers : can be nil
    // @listener : can be nil

    public void callGetMethod(String path, Map<String, Object> header,
                              final WSResponse objWsResponse) {

        this.objWsResponse = objWsResponse;

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class)
                .getData(path, header);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponse: ", "Code - " + response.code());

                Log.d("onResponse: ", "hhh - " + response.body().toString());

                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("onResponse: ", "result - " + result);


                //Log.d("onResponse: ", "data - " + String.valueOf(data.get("access_did")).replace("\"", ""));
                objWsResponse.onSuccess(result, response.code());


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                objWsResponse.onFailure(call.toString());
            }
        });
    }

    public void callPostMethod(String path, Map<String, Object> header, Object body,
                               final WSResponse objWsResponse) {


        this.objWsResponse = objWsResponse;

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class).postData(path, header, body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponse: ", "Code -@@ " + response.code());


                try {
                    String result = "";
                    if (result != null) {
                        result = response.body().string();
                        Log.d("onResponse: ", "result - " + result);
                    }

                    objWsResponse.onSuccess(result, response.code());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                objWsResponse.onFailure(call.toString());
            }
        });
    }

    public void callPutMethod(String path, Map<String, Object> header, Object body, final WSResponse objWsResponse) {

        this.objWsResponse = objWsResponse;

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class).putData(path, header, body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponse: ", "Code - " + response.code());


                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("onResponse: ", "result - " + result);


                //Log.d("onResponse: ", "data - " + String.valueOf(data.get("access_did")).replace("\"", ""));
                objWsResponse.onSuccess(result, response.code());


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                objWsResponse.onFailure(call.toString());
            }
        });
    }

    public void callDeleteMethod(String path, Map<String, Object> header, final WSResponse objWsResponse) {

        this.objWsResponse = objWsResponse;

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class).deleteData(path, header);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponse: ", "Code - " + response.code());

                String result = null;
                if (response.code() == 200) {
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("onResponse: ", "result - " + result);
                } else {
                    try {
                        result = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("onResponse: ", "result - " + result);
                //Log.d("onResponse: ", "data - " + String.valueOf(data.get("access_did")).replace("\"", ""));
                objWsResponse.onSuccess(result, response.code());


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                objWsResponse.onFailure(call.toString());
            }
        });
    }
}
