package com.example.raghav.chefiermain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.raghav.chefiermain.Models.SavedDishes;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddDishActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    ImageView DishImage;
    EditText DishName;
    EditText Description;
    Button AddImage;
    Button AddDish;
    ProgressBar progressBar;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference("Dishes");
    private DatabaseReference mChildReference = mRootReference.child("saved Dishes");
    private StorageReference dishImageReference = firebaseStorage.getReference("DISH IMAGES"), imageRef;

    UploadTask uploadTask;
    Uri selectedImage;
    public Uri downloadUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        DishImage = (ImageView) findViewById(R.id.image_dish);
        DishName = (EditText) findViewById(R.id.dish_name);
        Description = (EditText) findViewById(R.id.dish_descrip);
        AddImage = (Button) findViewById(R.id.add_img_btn);
        AddDish = (Button) findViewById(R.id.add_btn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(view);
            }
        });


        AddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String dishName = DishName.getText().toString();
                final String description = Description.getText().toString();

                imageRef = dishImageReference.child("saved dish images/"+selectedImage.getLastPathSegment());

                uploadTask = imageRef.putFile(selectedImage);
                

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                Toast.makeText(AddDishActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);
                                SavedDishes savedDishes = new SavedDishes(dishName, description, downloadUrl.toString());
                                String id = mChildReference.push().getKey();

                                mChildReference.child(id).setValue(savedDishes);

                                Intent intent = new Intent(AddDishActivity.this, OrdersScreen.class);
                                startActivity(intent);

                            }
                        });
            }
        });
    }

    public void selectImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(AddDishActivity.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage = imageReturnedIntent.getData();
                    DishImage.setImageURI(selectedImage);
                }
        }
    }

}