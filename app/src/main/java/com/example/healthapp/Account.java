package com.example.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {
    TextView loggedinas;
    FirebaseAuth FAuth;
    FirebaseUser FUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        loggedinas = findViewById(R.id.email);
        FAuth = FirebaseAuth.getInstance();
        FUser = FirebaseAuth.getInstance().getCurrentUser();

        loggedinas.setText(FUser.getEmail());//display user's email they are logged in with.


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {   case R.id.account:
                    return true;

                    case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
                    return true;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),about.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.details:
                        startActivity(new Intent(getApplicationContext(), Details.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.n_text:
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                        overridePendingTransition(0, 0);
                        return true;
}
                return false;
            }
        });
    }
    public void logout(View View) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Start.class));
        finish();
    }
}
