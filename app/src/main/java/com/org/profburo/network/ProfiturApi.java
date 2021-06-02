package com.org.profburo.network;

import com.org.profburo.network.responsesEntities.login.LoginBody;
import com.org.profburo.network.responsesEntities.login.LoginResponse;
import com.org.profburo.network.responsesEntities.region.RegionResponse;
import com.org.profburo.network.responsesEntities.registration.RegisterBody;
import com.org.profburo.network.responsesEntities.school.SchoolResponse;
import com.org.profburo.network.responsesEntities.test.HasAnyCompletedTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfiturApi {
    @POST("entry/login")
    Call<LoginResponse> loginUser(@Body LoginBody loginBody);

    @POST("entry/register")
    Call<LoginResponse> registerUser(@Body RegisterBody registerBody);

    @POST("tests/hastests")
    Call<HasAnyCompletedTest> hasTests(@Body int userId);

    @GET("regions/getall")
    Call<List<RegionResponse>> getRegions();

    @GET("schools/get")
    Call<List<SchoolResponse>> getSchools(@Query("region_id") Integer regionId);


}
