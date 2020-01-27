package com.example.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

                bottomNavigationView.setSelectedItemId(R.id.about);

                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch(menuItem.getItemId())
                        {   case R.id.about:
                            return true;

                            case R.id.n_text:
                                startActivity(new Intent(getApplicationContext(), Notes.class));
                                overridePendingTransition(0, 0);
                                return true;

                            case R.id.home:
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                overridePendingTransition(0, 0);
                                return true;

                            case R.id.account:
                                startActivity(new Intent(getApplicationContext(), Account.class));
                                overridePendingTransition(0, 0);
                                return true;


                            case R.id.details:
                                startActivity(new Intent(getApplicationContext(), Details.class));
                                overridePendingTransition(0, 0);
                                return true;
                        }
                        return false;
                    }
                });
            }
        }

