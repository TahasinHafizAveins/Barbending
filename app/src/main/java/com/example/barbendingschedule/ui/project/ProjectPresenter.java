package com.example.barbendingschedule.ui.project;

import android.util.Log;
import android.widget.Toast;

import com.example.barbendingschedule.JsonApi.JsonApi;
import com.example.barbendingschedule.JsonApi.ServiceGenerator;
import com.example.barbendingschedule.Model.Bar;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectPresenter implements ProjectContract.Presenter {
    private ProjectContract.View mView;
    private Bar bar;

    public ProjectPresenter(ProjectContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllBar(String project_id) {

        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.getAllBars(project_id).enqueue(new Callback<List<Bar>>() {
            @Override
            public void onResponse(Call<List<Bar>> call, Response<List<Bar>> response) {
                Log.d("getProject:","execute");
                if(response.isSuccessful()){
                    List<Bar> projectList = response.body();
                    mView.renderProjects(projectList);
                }
            }

            @Override
            public void onFailure(Call<List<Bar>> call, Throwable throwable) {
                Log.d("getProject:","ussuccessful"+throwable.getMessage());
            }
        });


    }

    @Override
    public void deleteBar(Bar bar) {
        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.deleteBar(bar.get_id()).enqueue(new Callback<Bar>() {
            @Override
            public void onResponse(Call<Bar> call, Response<Bar> response) {
                if (response.isSuccessful()){
                    Bar responseBar = response.body();
                    mView.removeFromAdapter(responseBar);
                }
            }

            @Override
            public void onFailure(Call<Bar> call, Throwable throwable) {

            }
        });

    }

    @Override
    public void getExcel(String project_id) {
        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.getExcel(project_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.d("ShowExcel","get excel");
                   String path = mView.saveFile(response.body());

                   if (path != null){
                       Log.d("ShowExcel","File Saved");
                       mView.openFile(path);
                   }
                   else {
                       mView.showToast("failed to save file");
                   }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void getOptimizeExcel(String project_id, double bar_length) {

        JsonApi api = ServiceGenerator.createService(JsonApi.class);
        api.getOptimizeExcel(project_id,bar_length).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.d("ShowExcel","get Optimize excel");
                    String path = mView.saveFile(response.body());

                    if (path != null){
                        Log.d("ShowExcel","File Saved");
                        mView.openFile(path);
                    }
                    else {
                        Log.d("ShowExcel","Failed to Save file");
                        mView.showToast("failed to save file");
                    }
                }else {
                    Log.d("ShowExcel","Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

    }

}
