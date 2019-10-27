package com.example.barbendingschedule.ui.home.main;

import com.example.barbendingschedule.Model.Project;

import java.util.List;

public interface MainFragmentContract {
    interface Presenter{
        void getAllProject( String user_id);
        void deleteProject(Project project);
    }
    interface View {
        void showToast(String message);
        void renderProjects(List<Project> projectList);
        void addProject(Project project);
        void startProjectActivity(Project project);
        void deleteProject (Project project);
        void removeFromAdapter(Project project);
    }
}
