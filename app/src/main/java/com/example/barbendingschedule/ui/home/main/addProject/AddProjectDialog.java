package com.example.barbendingschedule.ui.home.main.addProject;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.barbendingschedule.Model.Project;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.main.MainFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddProjectDialog extends DialogFragment implements DialogContract.View {

    private TextInputLayout tiName, tiDescription,tiLocation;
    private TextInputEditText etName,etDescription,etLocation;
    private Button ok_btn;
    private Button cancel_btn;
    private String name,description,location;
    private AddProjectPresenter mPresenter;
    private String uid;


    public AddProjectDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AddProjectPresenter(this);

        this.uid = getArguments().getString("uid");
        Log.d("Uid",""+uid);


    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.90f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.add_project_dialog, null, false);
//        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.ThemeOverlay_AppCompat_Dialog).setView(view).create();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialog).setView(view).create();
        initView(view);

        /*WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount=0.0f;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);*/

        return alertDialog;
    }
    private void initView(View view) {

        tiName = view.findViewById(R.id.tiName);
        tiDescription = view.findViewById(R.id.tiDescription);
        tiLocation = view.findViewById(R.id.tiLocation);
        etName = view.findViewById(R.id.etName);
        etDescription = view.findViewById(R.id.etDescription);
        etLocation = view.findViewById(R.id.etLocation);

        ok_btn = view.findViewById(R.id.btnAdd);
        cancel_btn = view.findViewById(R.id.btnCancel);




        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etName.getText().toString().trim();
                description = etDescription.getText().toString().trim();
                location = etLocation.getText().toString().trim();

                Project project = new Project(name,description,location);

                boolean valid = mPresenter.validate(project);

                if(!valid){
                    return;
                }

                project.setUser_id(uid);
                mPresenter.saveProjectToDatabase(project);
                Log.d("OkPressed :","ok");
              //  DialogListener dialogListener = (DialogListener) getParentFragment();
//                assert dialogListener != null;
//                dialogListener.onFinishEditDialog(name,description,location);
//                dismiss();

            }
        });



        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    @Override
    public void showError(int fieldId, String massage) {
        switch (fieldId){
            case 1:
                tiName.setError(massage);
                etName.requestFocus();
                break;
            case 2:
                tiLocation.setError(massage);
                etLocation.requestFocus();
                break;

            case 3:
                tiDescription.setError(massage);
                etDescription.requestFocus();
                break;
        }
    }

    @Override
    public void clearError() {
        tiName.setErrorEnabled(false);
        tiLocation.setErrorEnabled(false);
        tiDescription.setErrorEnabled(false);
    }

    @Override
    public void sendData(Project project) {

        if(getParentFragment() instanceof MainFragment){
            MainFragment mainFragment = (MainFragment) getParentFragment();
            Log.d("mainFragment","getFragment");
            mainFragment.addProject(project);
        }else {
            Log.d("mainFragment"," Not getFragment");
        }

        dismiss();
    }

}
