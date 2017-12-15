package com.example.raghav.chefiermain.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raghav.chefiermain.Models.Orders;
import com.example.raghav.chefiermain.R;

import java.util.ArrayList;

/**
 * Created by Raghav on 15-12-2017.
 */

public class OrdersAdapter extends ArrayAdapter<Orders> {

    public OrdersAdapter(Context context, ArrayList<Orders> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.orders_list_item, parent, false);
        }

        Orders currentOrder = getItem(position);

        TextView quantity = (TextView) listItemView.findViewById(R.id.qty);

        quantity.setText(currentOrder.getQuantity());

        TextView DishName = (TextView) listItemView.findViewById(R.id.dish);

        DishName.setText(currentOrder.getDish());

        TextView Address = (TextView) listItemView.findViewById(R.id.address);

        Address.setText(currentOrder.getAddress());

        return listItemView;
    }
}