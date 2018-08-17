package com.example.sargis.jsonretrofit.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sargis.jsonretrofit.adapter.ImageAdapter;
import com.example.sargis.jsonretrofit.R;
import com.example.sargis.jsonretrofit.api.Result;
import com.example.sargis.jsonretrofit.client.RetrofitClient;
import com.example.sargis.jsonretrofit.client.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private ImageAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
        final RecyclerView recyclerView = findViewById(R.id.images_recycler);
        final LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ImageAdapter(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        initializationRetrofit();
    }

    private void initializationRetrofit() {
        final RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        final Call<List<Result>> call = client.getData();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(@NonNull Call<List<Result>> call,
                                   @NonNull Response<List<Result>> response) {
                List<Result> resultList = response.body();
                adapter.setData(resultList);
            }

            @Override
            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
