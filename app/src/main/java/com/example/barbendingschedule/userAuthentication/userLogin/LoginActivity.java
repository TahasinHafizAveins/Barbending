package com.example.barbendingschedule.userAuthentication.userLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbendingschedule.Model.User;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.HomeActivity;
import com.example.barbendingschedule.userAuthentication.userLogin.resetPassword.ResetPassword;
import com.example.barbendingschedule.userAuthentication.userRegistration.RegistrationActivity;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText email;
    EditText password;
    Button loginBtn;
    Button registrationBtn;
    Button resetBtn;
    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btn_login);
        registrationBtn = findViewById(R.id.link_signUp);
        resetBtn = findViewById(R.id.link_reset);

        loginPresenter = new LoginPresenter(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(email.getText().toString(), password.getText().toString());

                Boolean valid = loginPresenter.validate(user);
                if (!valid) {
                    return;
                }
                loginPresenter.signIn(user);

            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistrationActivity();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });


    }

    @Override
    public void showErrorToast(int fieldId, String massage) {
        switch (fieldId) {
            case 1:
                showToastOnError(massage);
                break;

            case 2:
                showToastOnError(massage);
                break;
        }


    }

    @Override
    public void showToastOnSuccess(String massage) {
        Toasty.success(this, massage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showToastOnError(String massage) {
        Toasty.error(this, massage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void resetPassword() {
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }

    @Override
    public void gotoRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
