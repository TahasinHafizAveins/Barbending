package com.example.barbendingschedule.ui.project.addDetails;

import android.util.Log;
import com.example.barbendingschedule.JsonApi.JsonApi;
import com.example.barbendingschedule.JsonApi.ServiceGenerator;
import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.BarType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarDetailPresenter implements BarDetailContract.Presenter {
    private BarDetailContract.View mView;

    public BarDetailPresenter(BarDetailContract.View mView) {
        this.mView = mView;
    }

    //Check if field is blank
    @Override
    public boolean validation(Bar bar) {
        mView.clearError();
        if (bar.getTitle().equals("")) {
            mView.showError(1, "Enter Title");
            return false;
        }
        if (bar.getNo_of_bars()<=0){
            mView.showError(2, "Number of bars should greater than 0");
            return false;
        }
        if (bar.getDia()<=0){
            mView.showError(3, "Bar dia should greater than 0");
            return false;
        }
        if (bar.getLength()>12.3) {
            mView.showError(4, "Bar length should less than 12.3");
            return false;
        }
        return true;
    }

    //Sending Values of bars
    @Override
    public void submit(Bar bar) {

        JsonApi jsonApi = ServiceGenerator.createService(JsonApi.class);

        jsonApi.saveBar(bar).enqueue(new Callback<Bar>() {
            @Override
            public void onResponse(Call<Bar> call, Response<Bar> response) {
                if (response.isSuccessful()){
                    Bar responseBar = response.body();
                    mView.sendData(responseBar);
                }
            }
            @Override
            public void onFailure(Call<Bar> call, Throwable throwable) {

            }
        });

    }

  //Spinner Value
    @Override
    public void getAllBarType() {

        JsonApi api = ServiceGenerator.createService(JsonApi.class);

        api.getBarType().enqueue(new Callback<List<BarType>>() {
            @Override
            public void onResponse(Call<List<BarType>> call, Response<List<BarType>> response) {
                Log.d("getAllBarType:","Successful ");
                if(response.isSuccessful()){
                    List<BarType> barTypeList = response.body();
                    mView.renderTypes(barTypeList);
                    Log.d("getAllBarType:","Successful ");
                }
            }
            @Override
            public void onFailure(Call<List<BarType>> call, Throwable throwable) {
                Log.d("getAllBarType:","ussuccessful "+throwable.getMessage());
            }
        });
    }


}
