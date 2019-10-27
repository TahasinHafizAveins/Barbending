package com.example.barbendingschedule.ui.home.main;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.barbendingschedule.Model.Project;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.BaseFragment;
import com.example.barbendingschedule.ui.home.HomeActivity;
import com.example.barbendingschedule.ui.home.main.addProject.AddProjectDialog;
import com.example.barbendingschedule.ui.project.ProjectActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.barbendingschedule.R.menu.project_menu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements MainFragmentContract.View{

    private MainFragmentContract.Presenter mPresenter;
    private FloatingActionButton flAddProject;
    private RecyclerView rvProjects;
    private ProjectAdapter adapter;
    private String name,description,location;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainFragmentPresenter(this);
        adapter = new ProjectAdapter(this);
    }

    private void openDialog() {
        Bundle bundle = new Bundle();
        bundle.putString("uid",getUid());

        AddProjectDialog addProjectDialog = new AddProjectDialog();
        addProjectDialog.setCancelable(false);
        addProjectDialog.setArguments(bundle);
        addProjectDialog.show(getChildFragmentManager(), "Dialog Box open");
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        android.view.View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;

    }

    private void initView(android.view.View view) {

        flAddProject = view.findViewById(R.id.addBtn);
        rvProjects = view.findViewById(R.id.recycler_view);
        rvProjects.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProjects.setAdapter(adapter);

        //fetch projects of User(uid)
        mPresenter.getAllProject(getUid());


        flAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }


    @Override
    public void showToast(String message) {

    }

    @Override
    public void renderProjects(List<Project> projectList) {
        for (Project project : projectList) {
            adapter.addProject(project);
        }

    }

    @Override
    public  void addProject(Project project){
        adapter.addProject(project);
    }

    @Override
    public void startProjectActivity(Project project) {
        Intent intent = new Intent(getContext(), ProjectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("project",project);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void deleteProject(Project project) {
        mPresenter.deleteProject(project);
    }

    @Override
    public void removeFromAdapter(Project project) {
        adapter.removeProject(project);
    }
}