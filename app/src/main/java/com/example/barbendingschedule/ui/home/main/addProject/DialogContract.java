package com.example.barbendingschedule.ui.home.main.addProject;

import com.example.barbendingschedule.Model.Project;

public interface DialogContract {
    interface Presenter{
       boolean validate(Project project);
        void saveProjectToDatabase(Project project);
    }
    interface View{
        void showError(int fieldId,String massage);
        void clearError();
        void sendData(Project project);
    }
}
