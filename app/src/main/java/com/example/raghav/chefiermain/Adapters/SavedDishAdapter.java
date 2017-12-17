package com.example.raghav.chefiermain.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raghav.chefiermain.Models.SavedDishes;
import com.example.raghav.chefiermain.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Raghav on 17-12-2017.
 */

public class SavedDishAdapter extends ArrayAdapter<SavedDishes> {

    List<SavedDishes> savedDishes;
    Context context;


    public SavedDishAdapter(@NonNull Context context, @NonNull List<SavedDishes> objects) {
        super(context, 0, objects);
        this.context = context;
        this.savedDishes = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.saveddish_list_item, parent, false);

        }

        SavedDishes currentDish = getItem(position);

        TextView dishName = (TextView) convertView.findViewById(R.id.dish_name);
        dishName.setText(currentDish.getDishName());


        ImageView dishImage = (ImageView) convertView.findViewById(R.id.saved_dish_image);
        if(currentDish.getPhotoUrl()==null){
            dishImage.setImageResource(R.drawable.ic_launcher_background);
        }
        else {
            Picasso.with(getContext()).load(currentDish.getPhotoUrl()).into(dishImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
        }
        return convertView;

    }
}
