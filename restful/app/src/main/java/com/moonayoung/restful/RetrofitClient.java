package com.moonayoung.restful;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //private static final String BASE_URL = "http://localhost:8000/";
    //private static final String BASE_URL = "http://10.0.2.16:8000/";
    //private static final String BASE_URL = "http://192.168.0.81:8000/";
    private static final String BASE_URL = "http://192.168.35.121:8000/";

    public static ApiService getApiService(){return getInstance().create(ApiService.class);}

    private static Retrofit getInstance(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request newRequest = chain.request().newBuilder()
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        }).build();
        class ResInterceptor implements Interceptor {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                return response.newBuilder()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .build();
            }
        }

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public String getBaseUrl(){
        return BASE_URL;
    }
}
