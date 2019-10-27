package com.example.barbendingschedule.ui.home.main.addProject;

import android.util.Log;

import com.example.barbendingschedule.JsonApi.JsonApi;
import com.example.barbendingschedule.JsonApi.ServiceGenerator;
import com.example.barbendingschedule.Model.Project;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProjectPresenter implements DialogContract.Presenter {
    private DialogContract.View mView;

    public AddProjectPresenter(DialogContract.View mView) {
        this.mView = mView;
    }


    @Override
    public boolean validate(Project project) {

        mView.clearError();
        if (project.getName().equals("")) {
            mView.showError(1, "Name is empty");
            return false;
        }
        if (project.getLocation().equals("")) {
            mView.showError(2, "Location is empty");
            return false;
        }
        if (project.getDescription().equals("")) {
            mView.showError(3, "Description is empty");
            return false;
        }

        return true;
    }

    @Override
    public void saveProjectToDatabase(final Project project) {

        JsonApi jsonApi = ServiceGenerator.createService(JsonApi.class);

        jsonApi.saveProject(project).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if(response.isSuccessful()){
                    Project responseProject = response.body();
                    Log.d("responseProject",responseProject.getLocation());
                    mView.sendData(responseProject);
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable throwable) {

            }
        });
    }
}
