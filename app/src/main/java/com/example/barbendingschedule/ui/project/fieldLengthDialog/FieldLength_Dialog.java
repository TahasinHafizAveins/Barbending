package com.example.barbendingschedule.ui.project.fieldLengthDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.project.ProjectActivity;
import com.example.barbendingschedule.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FieldLength_Dialog extends DialogFragment implements FieldLengthDialogContract.View {

    private TextInputLayout tiLength;
    private TextInputEditText etLength;
    private Button submit_btn;
    private Button cancel_btn;
    private String length,project_id;
    private double bar_length;
    private FieldLengthDialogContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FieldLengthDialogPresenter(this);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.activity_field_length__dialog, null, false);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialog).setView(view).create();
        initView(view);

        return alertDialog;
    }

    private void initView(View view) {




        tiLength = view.findViewById(R.id.tiLength);
        etLength = view.findViewById(R.id.etLength);

        submit_btn = view.findViewById(R.id.btn_Submit_dialog);
        cancel_btn = view.findViewById(R.id.btn_Cancel_Dialog);




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                length = etLength.getText().toString().trim();
                bar_length = Double.parseDouble(length);
                if (bar_length <= 0){
                    return;
                }
                if(getActivity() instanceof ProjectActivity){

                    //send value to activity
                    ProjectActivity activity = (ProjectActivity) getActivity();
                    activity.requestOptimizeFile(bar_length);
                    dismiss();
                }
               // Toast.makeText(getContext(), "Submit Pressed"+bar_length+project_id, Toast.LENGTH_SHORT).show();

            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                //Toast.makeText(getContext(), "Cancel Pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
