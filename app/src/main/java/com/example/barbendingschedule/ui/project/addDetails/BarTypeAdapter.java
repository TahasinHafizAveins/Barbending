package com.example.barbendingschedule.ui.project.addDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.barbendingschedule.Model.BarType;
import com.example.barbendingschedule.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BarTypeAdapter extends BaseAdapter {

    private List<BarType> barTypeList;
    private LayoutInflater inflater;


    public BarTypeAdapter(@NonNull Context context) {
        this.barTypeList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return barTypeList.size();
    }

    @Override
    public Object getItem(int i) {
        return barTypeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BarType barType = barTypeList.get(position);

        View rowView = inflater.inflate(R.layout.type_list,parent,false);

        ImageView imageView = rowView.findViewById(R.id.img_bar);

        Picasso.get().load(barType.getImg_url()).into(imageView);

        return rowView;
    }
    public void addAll(List<BarType> barTypeList){
        this.barTypeList = barTypeList;
        notifyDataSetChanged();
    }

}
