package com.example.raghav.chefiermain.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.raghav.chefiermain.Adapters.OrdersAdapter;
import com.example.raghav.chefiermain.Models.Orders;
import com.example.raghav.chefiermain.R;
import java.util.ArrayList;

/**
 * Created by RyanBansal on 12/17/17.
 */

public class TabFragment extends android.support.v4.app.Fragment {

    private ArrayList<Orders> orders = new ArrayList<>();

    public void setData(ArrayList<Orders> ordersArrayList) {
        orders=ordersArrayList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabfragment, container, false);

        ListView ordersList = (ListView) rootView.findViewById(R.id.listOfOrders);
        ordersList.setEmptyView(rootView.findViewById(R.id.EmptyView));

        OrdersAdapter ordersAdapter = new OrdersAdapter(rootView.getContext(), orders);
        ordersList.setAdapter(ordersAdapter);

        return rootView;
    }

}
