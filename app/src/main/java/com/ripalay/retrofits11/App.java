package com.ripalay.retrofits11;

import android.app.Application;

import com.ripalay.retrofits11.data.remote.FilmApi;
import com.ripalay.retrofits11.data.remote.RetrofitClient;

public class App extends Application {
    public static FilmApi api;
    private RetrofitClient retrofitClient;


    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideFilmApi();

    }
}
