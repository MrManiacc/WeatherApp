package me.jraynor.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiService {
    public static Retrofit getRetrofitInstance(String baseUrl) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
