package com.example.barbendingschedule.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.barbendingschedule.ui.project.ProjectActivity;
import com.example.barbendingschedule.userAuthentication.userLogin.LoginActivity;

public class BaseFragment extends Fragment {

    private HomeActivity activity;
    private ProjectActivity projectActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
    }


    public void reCreate(){
        activity.recreate();

    }
    public String getUid(){
        return activity.getUid();
    }

    public String getUserName(){
        return  activity.getUserName();
    }

    public String getPid(){
        return projectActivity.getPid();
    }
    public void gotoLoginActivity(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}
