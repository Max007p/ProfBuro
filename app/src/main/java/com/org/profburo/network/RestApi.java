package com.org.profburo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private static RestApi mInstance;
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";
    private Retrofit mRetrofit;

    private RestApi()
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RestApi getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new RestApi();
        }
        return mInstance;
    }

    public ProfiturApi getApi()
    {
        return mRetrofit.create(ProfiturApi.class);
    }
}
