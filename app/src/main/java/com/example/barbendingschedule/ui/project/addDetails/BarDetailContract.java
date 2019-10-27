package com.example.barbendingschedule.ui.project.addDetails;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.BarType;

import java.util.List;

public interface BarDetailContract {
    interface Presenter{
        boolean validation(Bar bar);
        void submit(Bar bar);
        void getAllBarType();
    }
    interface View{
        void showError(int fieldId,String massage);
        void clearError();
        void renderTypes(List<BarType> barTypeList);
        void sendData(Bar Bar);
    }
}
