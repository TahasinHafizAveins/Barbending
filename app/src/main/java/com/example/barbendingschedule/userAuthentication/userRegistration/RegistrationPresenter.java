package com.example.barbendingschedule.userAuthentication.userRegistration;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.barbendingschedule.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View mView;
    private static final String TAG = "RegistrationActivity";

    private DatabaseReference mDatabase;

    RegistrationPresenter(RegistrationContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(User user) {

        if (user.getEmail().isEmpty()) {
            mView.showErrorToast(1, "You Must enter your Email");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            mView.showErrorToast(1, "You Must enter valid Email");
            return false;
        }

        if (user.getPassword().length() < 6) {
            mView.showErrorToast(2, "Password Length must be more then 6 Letter");
            return false;
        }
        return true;
    }

    public void signUp(final User user) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser != null) {
                        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    FirebaseAuth.getInstance().signOut();
                                    mView.startLoginActivity();

                                } else {
                                    mView.showToastOnError("Unable to send verification code");
                                    mView.thisActivity();
                                }
                            }
                        });
                    } else {
                        mView.showToastOnError("User is null");
                    }

                } else {
                    mView.showToastOnError("Unable Create new User");
                }
            }
        });
    }


}
