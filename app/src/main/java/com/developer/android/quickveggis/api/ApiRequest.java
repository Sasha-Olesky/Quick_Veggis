package com.developer.android.quickveggis.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {
    public static final String MAIN_URL = "http://exceedwiz.com/qv/";
    static ApiRequest apiRequest;
    ApiInterface api;

    public static ApiRequest getInstance() {
        if (apiRequest == null) {
            apiRequest = new ApiRequest();
        }
        return apiRequest;
    }

    public ApiRequest() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
//        this.api = (ApiInterface) new Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(MAIN_URL).client(new OkHttpClient.Builder().addInterceptor(interceptor).build()).build().create(ApiInterface.class);
    }

    public ApiInterface getApi() {
        return this.api;
    }
}
