package com.example.raghav.chefiermain;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghav.chefiermain.Adapters.OrdersAdapter;
import com.example.raghav.chefiermain.Models.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrdersScreen extends AppCompatActivity {

    FirebaseAuth auth;


    private NavigationView navigationView;
    ImageView userimage;
    TextView useremail;
    TextView username;
    private ArrayList<Orders> orders;

    private OrdersAdapter mOrderAdapter;

    private ListView orderslist;
    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrdersDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_screen);

        orders = new ArrayList<Orders>();
        orderslist = (ListView) findViewById(R.id.listOfOrders);


        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mOrdersDatabaseReference = mFirebaseDatabase.getReference().child("orders");
        mOrderAdapter = new OrdersAdapter(this, orders);
        orderslist.setAdapter(mOrderAdapter);

        mOrdersDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Orders newOrder = child.getValue(Orders.class);
                    mOrderAdapter.add(newOrder);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Setting up navigation drawer
        navigationView = (NavigationView) findViewById(R.id.my_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item_1:
                        Intent intent = new Intent(OrdersScreen.this, AddDishActivity.class);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(OrdersScreen.this).toBundle();
                        startActivity(intent, bundle);
                        break;
                    case R.id.nav_sub_1:
                        auth.signOut();
                        Toast.makeText(OrdersScreen.this, "Signed Out", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        userimage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.userimage);

        username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);

        useremail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.useremail);
    }
}
