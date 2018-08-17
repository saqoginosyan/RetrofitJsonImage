package com.example.sargis.jsonretrofit.client;

import com.example.sargis.jsonretrofit.api.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("photos")
    Call<List<Result>> getData();
}
