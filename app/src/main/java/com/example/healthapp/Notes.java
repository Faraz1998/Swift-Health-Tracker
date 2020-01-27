package com.example.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;


public class Notes extends AppCompatActivity {
    static final String KEY_NOTE = "Note";
    Button S, E;
    ConstraintLayout one, two;
    EditText et;
    TextView N;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference USER_NOTES = db.document("User Notes/Notes");//firestore details

    String uid;
    FirebaseAuth fireAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Notes);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Notes:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.details:
                        startActivity(new Intent(getApplicationContext(), Details.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        //container 1
        et = findViewById(R.id.ET);
        S = findViewById(R.id.save);
        one = findViewById(R.id.C1);

        //container 2
        E = findViewById(R.id.edit);
        two = findViewById(R.id.C2);
        N = findViewById(R.id.notesss);

        fStore = FirebaseFirestore.getInstance();
        fireAuth = FirebaseAuth.getInstance();
        uid = fireAuth.getCurrentUser().getUid();
    }
        @Override
        protected void onStart() {
            super.onStart();
            USER_NOTES.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if (documentSnapshot.exists()) {
                        String Note = documentSnapshot.getString(KEY_NOTE);

                        N.setText("Notes: " + "\n"  + Note);
                    }
                }
            });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.INVISIBLE);
            }
        });

        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Note = et.getText().toString();

                Map<String, Object> detail = new HashMap<>();
                detail.put(KEY_NOTE, Note);

                USER_NOTES.set(detail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Notes.this, "Notes Saved", Toast.LENGTH_SHORT).show();
                                one.setVisibility(View.INVISIBLE);
                                two.setVisibility(View.VISIBLE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Notes.this, "Failed to save details", Toast.LENGTH_SHORT).show();
                                //   Log.d(TAG, e.toString()); //probably delete this if not needed!!?!!??!?!@@@@@@@
                            }
                        });
            }
        });

    }
    }


