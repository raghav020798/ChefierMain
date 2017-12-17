package com.example.raghav.chefiermain.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raghav.chefiermain.Models.Orders;
import com.example.raghav.chefiermain.R;

import java.util.List;

/**
 * Created by Raghav on 15-12-2017.
 */

/**
 * Created by RyanBansal on 12/8/17.
 */


public class OrdersAdapter extends ArrayAdapter<Orders> {

    public OrdersAdapter(@NonNull Context context, @NonNull List<Orders> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.orders_list_item, parent, false);

        }



        TextView t1 = (TextView) convertView.findViewById(R.id.qty);
        t1.setText(getItem(position).getQuantity());

        TextView t2 = (TextView) convertView.findViewById(R.id.dish);
        t2.setText(getItem(position).getDish());

        TextView t3 = (TextView) convertView.findViewById(R.id.address);
        t3.setText(getItem(position).getAddress());

//        Animation animation = AnimationUtils
//                .loadAnimation(getContext(), R.anim.fu);
//        convertView.startAnimation(animation);

        return convertView;
    }
}