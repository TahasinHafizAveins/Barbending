package com.example.barbendingschedule.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.main.MainFragment;
import com.example.barbendingschedule.ui.home.unverified.UnVerifiedFragment;
import com.example.barbendingschedule.userAuthentication.userLogin.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    HomePresenter mPresenter;
    private String uid, username;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private FragmentManager manager;
    private NavigationView mNavigationView;
    private Button homeBtn;
    private ImageButton logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d("HHHH","onCreate Called");
        toolbar = findViewById(R.id.toolbar);
        mPresenter = new HomePresenter(this);

    }
    public void onStart() {
        super.onStart();
        Log.d("HHHH","onStart Called");
        mPresenter.checkCurrentUser();

    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void reCreate() {
        recreate();
    }

    @Override
    public void checkVerifiedUser() {
        mPresenter.checkVerifiedUser();
    }

    @Override
    public void loadToolberName(String username) {
        this.username = username;
    }
    @Override
    public void loadMainFragment( String uid) {
        this.uid = uid;
        toolbar.setTitle(username);
        setSupportActionBar(toolbar);
        setUpNavigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new MainFragment()).commit();
    }

    @Override
    public void loadVerifiedFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,new UnVerifiedFragment()).commit();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainfragment_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout_mainFragment:
                mPresenter.logOut();
                break;

        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void logOut() {
        mPresenter.logOut();
    }

    public String getUid(){
        return this.uid;
    }

    public String getUserName(){
        return this.username;
    }

    public void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


    }

    public void setUpNavigationDrawer(){
        setupToolbar();
        manager = getSupportFragmentManager();
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navView) ;
        logoutBtn = findViewById(R.id.logout_button);
        homeBtn = findViewById(R.id.home_container);
       // homeBtn.setPaintFlags(homeBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); //text underline

        setfullwidth();

        final ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        
       //toolbar.setNavigationIcon(R.drawable.custom_icon);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadMainFragment();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.logOut();
            }
        });

    }

    private void setfullwidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mNavigationView.getLayoutParams();
        params.width = displayMetrics.widthPixels;
        mNavigationView.setLayoutParams(params);
    }
}