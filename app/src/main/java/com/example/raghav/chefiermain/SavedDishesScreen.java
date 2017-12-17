package com.example.raghav.chefiermain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.raghav.chefiermain.Adapters.SavedDishAdapter;
import com.example.raghav.chefiermain.Models.SavedDishes;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SavedDishesScreen extends AppCompatActivity {

    private ArrayList<SavedDishes> savedDishesArrayList;
    private SavedDishAdapter savedDishesAdapter;
    private ListView SavedDishesListView;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mSavedDishesReference = database.getReference().child("Dishes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_dishes_screen);

        database = FirebaseDatabase.getInstance();


        savedDishesArrayList = new ArrayList<SavedDishes>();
        SavedDishesListView = (ListView) findViewById(R.id.saved_dishes_list);
        savedDishesAdapter = new SavedDishAdapter(this, savedDishesArrayList);
        SavedDishesListView.setAdapter(savedDishesAdapter);

        mSavedDishesReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    SavedDishes savedDishes = child.getValue(SavedDishes.class);
                    String DishName = savedDishes.getDishName();
                   Log.v("sddd",DishName);
                    savedDishesAdapter.add(savedDishes);
                    savedDishesAdapter.notifyDataSetChanged();
                }
                savedDishesAdapter.notifyDataSetChanged();
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

    }
}
