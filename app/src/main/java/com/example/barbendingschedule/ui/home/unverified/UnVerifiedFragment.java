package com.example.barbendingschedule.ui.home.unverified;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnVerifiedFragment extends BaseFragment {

    private Button refresh;
    private Button reSend;
    private TextView email;
    private Button back_btn;

    public UnVerifiedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_un_verified, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        refresh = view.findViewById(R.id.refresh);
        reSend = view.findViewById(R.id.reSend);
        email =  view.findViewById(R.id.userEmail);
        back_btn = view.findViewById(R.id.back_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail());

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLoginActivity();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().getCurrentUser().reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user.isEmailVerified()) {
                                   reCreate();
                                }
                            }
                        });
            }
        });

        reSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSend.setEnabled(false);
                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            FirebaseAuth.getInstance().getCurrentUser().reload()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            reSend.setEnabled(true);
                                            Toast.makeText(getContext(),"Verification Mail sent",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                        else {

                            Toast.makeText(getContext(),"Unable to send Verification Mail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
