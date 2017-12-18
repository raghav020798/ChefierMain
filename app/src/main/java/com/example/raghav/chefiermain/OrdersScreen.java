package com.example.raghav.chefiermain;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.raghav.chefiermain.Adapters.MyFragmentAdapter;
import com.example.raghav.chefiermain.Authentication.LoginActivity;
import com.example.raghav.chefiermain.Models.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrdersScreen extends AppCompatActivity  {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    ImageView userimage;
    TextView useremail;
    TextView username;

    ArrayList<Orders> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_screen);



        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(OrdersScreen.this, LoginActivity.class));
                    finish();
                }
            }
        };

        orders = new ArrayList<>();

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Orders newOrder = child.getValue(Orders.class);
                    orders.add(newOrder);
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
        };
        mDatabaseReference.addChildEventListener(childEventListener);

        // Setting up action bar
        Toolbar myToolbar = findViewById(R.id.actionbar);
        setActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        // Setting up tabs
        final ViewPager viewPager = findViewById(R.id.viewpager);
        final MyFragmentAdapter myAdapter = new MyFragmentAdapter(getSupportFragmentManager(), orders);
        viewPager.setAdapter(myAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Setting up navigation drawer
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START, true);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.my_navigation_view);
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

                    case R.id.nav_item_2:
                        Intent intent1 = new Intent(OrdersScreen.this, SavedDishesScreen.class);
                        Bundle bundle1 = ActivityOptions.makeSceneTransitionAnimation(OrdersScreen.this).toBundle();
                        startActivity(intent1, bundle1);
                }
                return false;
            }
        });

        userimage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.userimage);

        username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);

        useremail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.useremail);

    }

    @Override
    public void onResume() {
        super.onResume();
            auth.addAuthStateListener(authListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null)
            auth.removeAuthStateListener(authListener);
    }
}
