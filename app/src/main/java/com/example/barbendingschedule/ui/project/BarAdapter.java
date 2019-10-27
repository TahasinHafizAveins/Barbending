package com.example.barbendingschedule.ui.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.project.showDetails.ShowBarDetails;
import com.example.barbendingschedule.utils.Constants;
import com.example.barbendingschedule.utils.Utils;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarHolder> {

    private ProjectActivity projectActivity;
    private LayoutInflater inflater;
    private List<Bar> barList;

    public BarAdapter(ProjectActivity projectActivity) {
        this.projectActivity = projectActivity;
        this.inflater = LayoutInflater.from(projectActivity);
        this.barList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BarAdapter.BarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.folding_cell_project_activity, parent, false);

        return new BarAdapter.BarHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BarAdapter.BarHolder holder, int position) {

        Bar bar = barList.get(position);
        holder.bind(bar);

    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    public void addBar(Bar bar) {
        barList.add(bar);
        int position = barList.indexOf(bar);
        notifyItemInserted(position);

    }


    public void removeBar(Bar bar) {
        int position = getPosition(bar);
        if (position != -1) {
            barList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public List<Bar> getBarList() {
        return this.barList;
    }

    private int getPosition(Bar bar) {

        for (Bar x : barList) {
            if (x.get_id().equals(bar.get_id())) {
                return barList.indexOf(x);
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class BarHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        TextView tvName,tvTitle;
        TextView tvLength,fdLength;
        TextView tvDia,fdDia;
        TextView tvNumberOfBar,fdNumberOfBar;
        ImageView ivBar_img;
        FoldingCell mFoldingCell;
        LinearLayout cont_a;
        LinearLayout cont_b;
        LinearLayout cont_c;
        LinearLayout cont_d;
        LinearLayout cont_e;
        LinearLayout cont_f;
        TextView tv_a;
        TextView tv_b;
        TextView tv_c;
        TextView tv_d;
        TextView tv_e;
        TextView tv_f;

        public BarHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.bar_name);
            tvTitle = itemView.findViewById(R.id.bar_title);
            tvNumberOfBar = itemView.findViewById(R.id.number_of_bar);
            tvDia = itemView.findViewById(R.id.bar_dia);
            tvLength = itemView.findViewById(R.id.bar_length);

            fdNumberOfBar = itemView.findViewById(R.id.fold_number_of_bar);
            fdDia = itemView.findViewById(R.id.fold_bar_dia);
            fdLength = itemView.findViewById(R.id.fold_bar_length);

            ivBar_img = itemView.findViewById(R.id.bar_image);
            mFoldingCell = itemView.findViewById(R.id.foldingCell_project);

            cont_a = itemView.findViewById(R.id.container_a);
            cont_b = itemView.findViewById(R.id.container_b);
            cont_c = itemView.findViewById(R.id.container_c);
            cont_d = itemView.findViewById(R.id.container_d);
            cont_e = itemView.findViewById(R.id.container_e);
            cont_f = itemView.findViewById(R.id.container_f);

            tv_a = itemView.findViewById(R.id.tv_a);
            tv_b = itemView.findViewById(R.id.tv_b);
            tv_c = itemView.findViewById(R.id.tv_c);
            tv_d = itemView.findViewById(R.id.tv_d);
            tv_e = itemView.findViewById(R.id.tv_e);
            tv_f = itemView.findViewById(R.id.tv_f);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }


        public void bind(Bar bar) {

            String number_of_bar = Integer.toString(bar.getNo_of_bars());
            String bar_dia = Integer.toString(bar.getDia());

            tvLength.setText(Utils.getTwodecimalPoint(bar.getLength()));
            fdLength.setText(Utils.getTwodecimalPoint(bar.getLength()));
            Log.d("LengthCall", "" + bar.getLength() + " ," + bar.getBar_type());

            tvName.setText(bar.getTitle());
            tvTitle.setText(bar.getTitle());
            tvNumberOfBar.setText(number_of_bar);
            fdNumberOfBar.setText(number_of_bar);
            tvDia.setText(bar_dia);
            fdDia.setText(bar_dia);

            Picasso.get().load(bar.getBar_img()).fit().fit()
                    .into(ivBar_img);

            if (bar.getA() > 0){
                cont_a.setVisibility(View.VISIBLE);
                tv_a.setText(String.valueOf(bar.getA()));
            } else {
                cont_a.setVisibility(View.GONE);
            }

            if (bar.getB() > 0){
                cont_b.setVisibility(View.VISIBLE);
                tv_b.setText(String.valueOf(bar.getB()));
            } else {
                cont_b.setVisibility(View.GONE);
            }

            if (bar.getC() > 0){
                cont_c.setVisibility(View.VISIBLE);
                tv_c.setText(String.valueOf(bar.getC()));
            } else {
                cont_c.setVisibility(View.GONE);
            }

            if (bar.getD() > 0){
                cont_d.setVisibility(View.VISIBLE);
                tv_d.setText(String.valueOf(bar.getD()));
            } else {
                cont_d.setVisibility(View.GONE);
            }

            if (bar.getE() > 0){
                cont_e.setVisibility(View.VISIBLE);
                tv_e.setText(String.valueOf(bar.getE()));
            } else {
                cont_e.setVisibility(View.GONE);
            }

            if (bar.getF() > 0){
                cont_f.setVisibility(View.VISIBLE);
                tv_f.setText(String.valueOf(bar.getF()));
            } else {
                cont_f.setVisibility(View.GONE);
            }
        }


        @Override
        public boolean onLongClick(View v) {
            openDeleteAlert(v);
            return false;
        }

        @Override
        public void onClick(View v) {

            mFoldingCell.toggle(false);

            /*Toast.makeText(projectActivity, "position clicked" + getAdapterPosition(), Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            //bundle.putString("uid",getUid());
            bundle.putSerializable(Constants.BAR, barList.get(getAdapterPosition()));

            ShowBarDetails showBarDetails = new ShowBarDetails();
            showBarDetails.setCancelable(false);
            showBarDetails.setArguments(bundle);
            showBarDetails.show(projectActivity.getSupportFragmentManager(), "fragmentDialog");*/
        }

        private void openDeleteAlert(View view){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(projectActivity);

            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("Do you want to Delete the Bar");
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            projectActivity.deleteBar(barList.get(getAdapterPosition()));
                            //Toast.makeText(projectActivity,"You clicked yes button",Toast.LENGTH_LONG).show();
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
