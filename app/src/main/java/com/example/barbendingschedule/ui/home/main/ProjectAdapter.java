package com.example.barbendingschedule.ui.home.main;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbendingschedule.Model.Project;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.main.helper.SwipeItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder> {

    private MainFragment mainFragment;
    private LayoutInflater inflater;
    private List<Project> projectList;
    private RecyclerView mRecyclerView;

    public ProjectAdapter(MainFragment mainFragment,RecyclerView recyclerView) {
        this.mainFragment = mainFragment;
        this.inflater = LayoutInflater.from(mainFragment.getContext());
        this.projectList = new ArrayList<>();
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_project,parent,false);
        return new ProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {

        Project project = projectList.get(position);
        holder.bind(project);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public void addProject(Project project){
        projectList.add(project);
        int position = projectList.indexOf(project);
        notifyItemInserted(position);

    }

    public void removeProject(Project project){
        int position = getPosition(project);
        if (position != -1){
            projectList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public Project getProject(int position){
        return this.projectList.get(position);
    }

    public void restoreProject(Project project){
        projectList.add(project);
        int position = projectList.indexOf(project);
        notifyItemInserted(position);
    }

    private int getPosition(Project project){
        for (Project x:projectList){
            if (x.get_id().equals(project.get_id())){
                return projectList.indexOf(x);
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    public class ProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvName;
        public RelativeLayout viewForeground, viewBacground;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);

            tvName= itemView.findViewById(R.id.project_name);
            viewBacground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        public void bind(Project project){
            tvName.setText(project.getName());
        }

        @Override
        public void onClick(View view) {
            mainFragment.startProjectActivity(projectList.get(getAdapterPosition()));

        }

        @Override
        public boolean onLongClick(View v) {
           // open(v);
            return false;
        }

        private void open(View view){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mainFragment.getContext());

            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("Do you want to Delete the project");
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            mainFragment.deleteProject(projectList.get(getAdapterPosition()));
                            //Toast.makeText(mainFragment.getContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                        }
                    });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
}