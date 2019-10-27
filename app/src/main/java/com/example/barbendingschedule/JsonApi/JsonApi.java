package com.example.barbendingschedule.JsonApi;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.BarType;
import com.example.barbendingschedule.Model.Project;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {

    @POST("/api/projects")
    Call<Project> saveProject(@Body Project project);

    @POST("/api/bars")
    Call<Bar> saveBar(@Body Bar bar);

    @GET("/api/bars/{project_id}")
    Call<List<Bar>> getAllBars(@Path("project_id") String project_id);

    ////Spinner Value Image of bar types
    @GET("/api/bartypes")
    Call<List<BarType>> getBarType();

    //create and open excel file
    @GET("/api/bars/{project_id}/excel")
    Call <ResponseBody> getExcel(@Path ("project_id") String project_id);

    //create and open Optimize file
    @GET("/api/bars/{project_id}/optimize/{bar_length}")
    Call <ResponseBody> getOptimizeExcel(@Path ("project_id") String project_id, @Path("bar_length") double bar_length);


    //fetch projects of User(uid)
    @GET("/api/projects/{uid}")
    Call<List<Project>> getAllProjects(@Path("uid") String uid);

    //delete Bars
    @DELETE("/api/bars/{id}")
    Call<Bar> deleteBar(@Path("id") String bar_id);

    //delete Projects
    @DELETE("/api/projects/{id}")
    Call<Project> deleteProject(@Path("id") String project_id);




//    @GET("/api/projects/{bid}")
//    Call<List<Bar>> getAllBars(@Path("bid") String bid);

 /*   @GET("/api/projects/uid/{_id}")
    Call<List<Project>> getProjectDetails(@Path ("_id") String _id);

@POST("/api/projects")
    Call<BarDetail> saveBarDetail(@Body BarDetail barDetail);

    @FormUrlEncoded
    @POST("/api/projects/uid/{_id}")
    Call<BarType>sendBarTypeValue(
            @Field("img_url") String img_url,
            @Field("equation") String equation,
            @Field("a") String a,
            @Field("b") String b,
            @Field("c") String c,
            @Field("d") String d,
            @Field("e") String e,
            @Field("f") String f
    );*/


}

