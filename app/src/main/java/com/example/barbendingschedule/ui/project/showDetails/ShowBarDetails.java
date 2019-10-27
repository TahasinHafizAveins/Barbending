package com.example.barbendingschedule.ui.project.showDetails;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.utils.Constants;

public class ShowBarDetails extends DialogFragment {
    private Bar bar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       this.bar = (Bar) getArguments().getSerializable(Constants.BAR);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.show_bar_details_dailog, null, false);
//        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.ThemeOverlay_AppCompat_Dialog).setView(view).create();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialog).setView(view).create();
        initView(view);
        return alertDialog;
    }

    private void initView(View view) {

        LinearLayout cont_a = view.findViewById(R.id.container_a);
        LinearLayout cont_b = view.findViewById(R.id.container_b);
        LinearLayout cont_c = view.findViewById(R.id.container_c);
        LinearLayout cont_d = view.findViewById(R.id.container_d);
        LinearLayout cont_e = view.findViewById(R.id.container_e);
        LinearLayout cont_f = view.findViewById(R.id.container_f);

        TextView tv_a = view.findViewById(R.id.tv_a);
        TextView tv_b = view.findViewById(R.id.tv_b);
        TextView tv_c = view.findViewById(R.id.tv_c);
        TextView tv_d = view.findViewById(R.id.tv_d);
        TextView tv_e = view.findViewById(R.id.tv_e);
        TextView tv_f = view.findViewById(R.id.tv_f);

        Button ok = view.findViewById(R.id.btnOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (bar.getA() > 0){
            cont_a.setVisibility(View.VISIBLE);
            tv_a.setText(String.valueOf(bar.getA()));
        }
        else {
            cont_a.setVisibility(View.GONE);
        }

        if (bar.getB() > 0){
            cont_b.setVisibility(View.VISIBLE);
            tv_b.setText(String.valueOf(bar.getB()));
        }
        else {
            cont_b.setVisibility(View.GONE);
        }

        if (bar.getC() > 0){
            cont_c.setVisibility(View.VISIBLE);
            tv_c.setText(String.valueOf(bar.getC()));
        }
        else {
            cont_c.setVisibility(View.GONE);
        }

        if (bar.getD() > 0){
            cont_d.setVisibility(View.VISIBLE);
            tv_d.setText(String.valueOf(bar.getD()));
        }
        else {
            cont_d.setVisibility(View.GONE);
        }

        if (bar.getE() > 0){
            cont_e.setVisibility(View.VISIBLE);
            tv_e.setText(String.valueOf(bar.getE()));
        }
        else {
            cont_e.setVisibility(View.GONE);
        }

        if (bar.getF() > 0){
            cont_f.setVisibility(View.VISIBLE);
            tv_f.setText(String.valueOf(bar.getF()));
        }
        else {
            cont_f.setVisibility(View.GONE);
        }
    }
}
