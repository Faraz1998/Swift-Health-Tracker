package com.example.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class Details extends AppCompatActivity {

    //constants (key for values)
    static final String TAG = "Details";
    static final String KEY_GENDER = "Gender";
    static final String KEY_AGE = "Age";
    static final String KEY_WEIGHT = "Weight";
    static final String KEY_HEIGHT = "Height";

    EditText gender;
    EditText age;
    EditText weight;
     EditText height;
     TextView userdetails;
     Button add;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference USER_DETAILS = db.document("User Details/details");//firestore details

    String uid;
    FirebaseAuth fireAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.details);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {   case R.id.details:
                    return true;

                    case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
                    return true;

                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.notes:
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                        overridePendingTransition(0, 0);
                        return true;}
                return false;
            }
        });

        gender = findViewById(R.id.EtGender);
        age = findViewById(R.id.EtAge);
        weight = findViewById(R.id.EtWeight);
        height = findViewById(R.id.EtHeight);
        userdetails = findViewById(R.id.Udetails);
        add = findViewById(R.id.btnAdd2);
        fStore = FirebaseFirestore.getInstance();
        fireAuth = FirebaseAuth.getInstance();
        uid = fireAuth.getCurrentUser().getUid();
    }

        @Override
        protected void onStart() {
            super.onStart();
            USER_DETAILS.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if (documentSnapshot.exists()) {
                        String Gender = documentSnapshot.getString(KEY_GENDER);
                        String Age = documentSnapshot.getString(KEY_AGE);
                        String Weight = documentSnapshot.getString(KEY_WEIGHT);
                        String Height = documentSnapshot.getString(KEY_HEIGHT);

                        userdetails.setText("Gender: " + Gender + "\n" + "Age: " + Age + "\n" + "Weight: " + Weight + "\n" + "Height: " + Height);
                    }
                }
            });
        }
    //on add detail click
    public void Save(View v){
        String Gender = gender.getText().toString();
        String Age = age.getText().toString();
        String Weight = weight.getText().toString();
        String Height = height.getText().toString();

        Map<String, Object> details = new HashMap<>();
        details.put(KEY_GENDER, Gender);
        details.put(KEY_AGE, Age);
        details.put(KEY_WEIGHT, Weight);
        details.put(KEY_HEIGHT, Height);

        USER_DETAILS.set(details)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
         @Override
         public void onSuccess(Void aVoid) {
             Toast.makeText(Details.this, "Details Saved", Toast.LENGTH_SHORT).show();
         }
     })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Details.this, "Failed to save details", Toast.LENGTH_SHORT).show();
                     //   Log.d(TAG, e.toString()); //probably delete this if not needed!!?!!??!?!@@@@@@@
                    }
                });
//to display info for a user
        //get user data
      //  USER_DETAILS.get()
        //        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        //            @Override
      //              public void onSuccess(DocumentSnapshot documentSnapshot){
          //              if (documentSnapshot.exists()){
            //                String Gender = documentSnapshot.getString(KEY_GENDER);
              //            String Age = documentSnapshot.getString(KEY_AGE);
               //             String Weight = documentSnapshot.getString(KEY_WEIGHT);
               //             String Height = documentSnapshot.getString(KEY_HEIGHT);

  //                          userdetails.setText("Gender: " + Gender + "\n" + "Age: " + Age + "\n" + "Weight: " + Weight + "\n" + "Height: " + Height);
  //                          startActivity(new Intent(getApplicationContext(),displaydetails.class));
    //                        //maybe delete thiss!!!!"£!"
      //                  } else{
        //                    Toast.makeText(Details.this, "dont extist ", Toast.LENGTH_SHORT).show();
          //              }
            //        }
              //  });
    }}

