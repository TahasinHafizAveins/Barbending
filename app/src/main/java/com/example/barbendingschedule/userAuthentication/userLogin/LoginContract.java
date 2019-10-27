package com.example.barbendingschedule.userAuthentication.userLogin;

import com.example.barbendingschedule.Model.User;

public interface LoginContract {

    interface Presenter {
        Boolean validate(User user);
        void signIn(User user);
    }

    interface View {

        void showErrorToast(int fieldId, String massage);
        void showToastOnSuccess(String massage);
        void showToastOnError(String massage);
        void startHomeActivity();
        void resetPassword();
        void gotoRegistrationActivity();

    }
}
