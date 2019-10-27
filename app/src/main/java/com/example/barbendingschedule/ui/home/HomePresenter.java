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
            mView.loadToolberName(mCurrentUser.getEmail());
            mView.loadMainFragment(mCurrentUser.getUid());
        }else{
            mView.loadVerifiedFragment();
        }
    }

    @Override
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        mView.reCreate();
    }


}