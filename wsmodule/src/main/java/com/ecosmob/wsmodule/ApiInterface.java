package com.ecosmob.wsmodule;

/**
 * Created by hardik on 27/1/17.
 */

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET
    Call<ResponseBody> getData(@Url String path, @HeaderMap Map<String, Object> headers);

    @POST
    Call<ResponseBody> postData(@Url String path, @HeaderMap Map<String, Object> headers,
                                @Body Object object);

    @PUT
    Call<ResponseBody> putData(@Url String path, @HeaderMap Map<String, Object> headers,
                               @Body Object object);

    @DELETE
    Call<ResponseBody> deleteData(@Url String path, @HeaderMap Map<String, Object> headers);
}
