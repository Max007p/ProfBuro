package com.org.profburo.network;

import com.org.profburo.entities.UserAnswers;
import com.org.profburo.network.responsesEntities.login.LoginBody;
import com.org.profburo.network.responsesEntities.login.LoginResponse;
import com.org.profburo.network.responsesEntities.participiants.ParticipiantsResponse;
import com.org.profburo.network.responsesEntities.region.RegionResponse;
import com.org.profburo.network.responsesEntities.registration.RegisterBody;
import com.org.profburo.network.responsesEntities.school.SchoolResponse;
import com.org.profburo.network.responsesEntities.teachers.TeacherResponse;
import com.org.profburo.network.responsesEntities.test.AnswerQuestionBind;
import com.org.profburo.network.responsesEntities.test.HasAnyCompletedTest;
import com.org.profburo.network.responsesEntities.test.TestInsertResponse;
import com.org.profburo.network.responsesEntities.test.TestResults;
import com.org.profburo.network.responsesEntities.test.TestStatistic;
import com.org.profburo.network.responsesEntities.test.TestsResponse;

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

    @POST("tests/setresults")
    Call<TestInsertResponse> setTestsResults(@Body UserAnswers answers);

    @POST("tests/getresults")
    Call<List<TestResults>> getTestsResults(@Body int resultId);

    @GET("regions/getall")
    Call<List<RegionResponse>> getRegions();

    @GET("schools/get")
    Call<List<SchoolResponse>> getSchools(@Query("region_id") Integer regionId);

    @GET("tests/getall/active")
    Call<List<TestsResponse>> getTestsList(@Query("user_id") Integer userId);

    @GET("tests/get")
    Call<List<AnswerQuestionBind>> getTestBody(@Query("test_id") Integer testId);

    @GET("users/get/teachers")
    Call<List<TeacherResponse>> getTeachers();

    @GET("users/get/all")
    Call<List<ParticipiantsResponse>> getAll(@Query("fio") String fio);

    @GET("tests/get/statistic")
    Call<List<TestStatistic>> getTestStatistic();



}
