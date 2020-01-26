package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class displaydetails extends AppCompatActivity {

    TextView udetails;
    Button edit;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
   // DocumentReference USER_DETAILS = db.document("User Details/details");//firestore details

    String uid;
    FirebaseAuth fireAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        udetails = findViewById(R.id.UDet);
        edit = findViewById(R.id.btnedit);

        fStore = FirebaseFirestore.getInstance();
        fireAuth = FirebaseAuth.getInstance();
        uid = fireAuth.getCurrentUser().getUid();

        DocumentReference documentreference = fStore.collection("User Details").document(uid);
        documentreference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    udetails.setText(documentSnapshot.getString("Gender"));
            }

        });
    }
}

//edit.setOnClickListener(new View.OnClickListener()
//@Override
//public void onClick (View v){
 //       startActivity(new Intent(getApplicationContext(),Details.class));
   //     }

