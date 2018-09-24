package com.ypst.primeyz.rxjavasamples;

import android.app.Application;

import com.google.gson.Gson;
import com.ypst.primeyz.rxjavasamples.networks.CountryAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yepyaesonetun on 9/24/18.
 **/

public class RxJavaApp extends Application {
    public static final String TAG = "RxJavaApp";
    private CountryAPI theApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initCountryApi();
    }

    public CountryAPI getCountryAPI(){
        return theApi;
    }

    private void initCountryApi() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(CountryAPI.class);
    }
}
