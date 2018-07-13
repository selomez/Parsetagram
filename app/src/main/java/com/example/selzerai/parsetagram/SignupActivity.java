package com.example.selzerai.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.parceler.Parcel;

@Parcel
public class SignupActivity extends AppCompatActivity {
    EditText username;
    EditText handle;
    EditText password;
    Button signup;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.etName);
        handle = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        signup = findViewById(R.id.btnSignUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting values to user input
                ParseUser user = new ParseUser();

                user.setUsername(String.valueOf(username.getText()));
                user.setPassword(String.valueOf(password.getText()));
                user.put("handle",String.valueOf(handle.getText()));

                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(SignupActivity.this, "Login to Continue!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
