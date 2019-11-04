package com.example.barbendingschedule.userAuthentication.userRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbendingschedule.Model.User;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.HomeActivity;
import com.example.barbendingschedule.userAuthentication.userLogin.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

    RegistrationContract.Presenter registrationPresenter;


    private TextInputLayout email_tl, password_tl;
    private TextInputEditText email,password;
    Button registrationBtn;
    Button loginPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);


        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginPageBtn=findViewById(R.id.login);
        registrationBtn=findViewById(R.id.btn_Registration);

        registrationPresenter =new RegistrationPresenter(this);

        onClickRegistrationBtn();
        onClickLoginBtn();
    }

    private void onClickRegistrationBtn() {

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User users =new User(email.getText().toString(),password.getText().toString());

                registrationPresenter.signUp(users);

            }
        });

    }
    private void onClickLoginBtn() {
        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });

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
    public void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void thisActivity() {
        startActivity(getIntent());
    }

}
