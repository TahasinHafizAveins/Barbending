package com.example.barbendingschedule.userAuthentication.userRegistration;


import com.example.barbendingschedule.Model.User;

public interface RegistrationContract {

    interface Presenter{

        boolean validate(User user);
        void signUp(User user);

    }

    interface View{
        void showToastOnSuccess(String massage);
        void showToastOnError(String massage);
        void showErrorToast(int fieldId, String massage);
        void startHomeActivity();
        void startLoginActivity();
        void thisActivity();
    }
}
