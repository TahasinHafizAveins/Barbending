package com.example.barbendingschedule.ui.home;

public interface HomeContract {
    interface Presenter{
        void checkCurrentUser();
        void checkVerifiedUser();
        void loadMainFragment();
        void logOut();
    }
    interface View{
        void startLoginActivity();
        void reCreate();
        void checkVerifiedUser();
        void loadMainFragment(String uid);
        void loadVerifiedFragment();
        void loadToolberName(String username);
        void logOut();
    }
}
