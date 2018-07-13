package com.example.selzerai.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void logout(View view) {
        ParseUser.logOut();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void launchCreate(View view) {
        Intent intent = new Intent(ProfileActivity.this, CameraActivity.class);
        startActivity(intent);
    }
    public void launchTimeline(View view) {
        Intent intent = new Intent(ProfileActivity.this, TimelineActivity.class);
        startActivity(intent);
    }
}
