package com.example.vybe;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter<VisionBoardCategory> {

    public CategoriesAdapter(Context context, ArrayList<VisionBoardCategory> visionBoardCategoryArrayList) {
        super(context, 0, visionBoardCategoryArrayList);
    }

    public CategoriesAdapter (@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        VisionBoardCategory currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.mCategoriesImage());
            textViewName.setText(currentItem.mCategories());
        }

        return convertView;

    }
}
