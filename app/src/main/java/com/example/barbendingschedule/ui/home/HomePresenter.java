package com.example.barbendingschedule.ui.home;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View mView;
    private FirebaseUser mCurrentUser;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        this.mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void checkCurrentUser() {
        if (mCurrentUser==null)
        {
            mView.startLoginActivity();
        }
        else{
            mView.checkVerifiedUser();
        }
    }

    @Override
    public void checkVerifiedUser() {
        Log.d("HHHH",mCurrentUser.isEmailVerified()+"");
        if (mCurrentUser.isEmailVerified())
        {
            String username = usernameFromEmail(mCurrentUser.getEmail());
            mView.loadToolberName(username);
            mView.loadMainFragment(mCurrentUser.getUid());
        }else{
            mView.loadVerifiedFragment();
        }
    }

    @Override
    public void loadMainFragment() {
        String username = usernameFromEmail(mCurrentUser.getEmail());
        mView.loadToolberName(username);
        mView.loadMainFragment(mCurrentUser.getUid());
    }

    @Override
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        mView.reCreate();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}