package com.example.barbendingschedule.ui.project;

import com.example.barbendingschedule.Model.Bar;

import java.util.List;

import okhttp3.ResponseBody;

public interface ProjectContract {
    interface Presenter {
        void getAllBar(String project_id);

        void deleteBar(Bar bar);

        void getExcel(String project_id);

        void getOptimizeExcel(String project_id, double bar_length);
    }

    interface View {
        void getProjectId(String p_id);

        void renderProjects(List<Bar> barList);

        void deleteBar(Bar bar);

        void removeFromAdapter(Bar bar);

        String saveFile(ResponseBody responseBody);

        void showToast(String massage);

        void openFile(String path);

        void requestOptimizeFile(double bar_length);
    }
}
