package com.example.barbendingschedule.ui.home.main;

import android.util.Log;

import com.example.barbendingschedule.JsonApi.JsonApi;
import com.example.barbendingschedule.JsonApi.ServiceGenerator;
import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    MainFragmentContract.View mView;


    MainFragmentPresenter(MainFragmentContract.View mView) {
        this.mView = mView;
    }

    //fetch projects of User(uid)
    @Override
    public void getAllProject(String uid) {

        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.getAllProjects(uid).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                Log.d("getProject:","execute");
                if(response.isSuccessful()){
                    List<Project> projectList = response.body();
                    mView.renderProjects(projectList);
                    Log.d("getProject:","Successful");
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable throwable) {
                Log.d("getProject:","ussuccessful"+throwable.getMessage());
            }
        });


    }

    @Override
    public void deleteProject(Project project) {
        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.deleteProject(project.get_id()).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project responseProject = response.body();
                mView.removeFromAdapter(responseProject);
            }

            @Override
            public void onFailure(Call<Project> call, Throwable throwable) {

            }
        });
    }
}