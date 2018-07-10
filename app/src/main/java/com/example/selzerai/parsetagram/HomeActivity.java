package com.example.selzerai.parsetagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.selzerai.parsetagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private static final String imagePath = null;
    private EditText descriptionInput;
    private Button createButton;
    private Button refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//TODO - LOOK AT VIDEO 5 IN BEGINNING AND SEE IF THIS IS NECESSARY
       descriptionInput = findViewById(R.id.etDescription);
       createButton = findViewById(R.id.btnCreate);
       refreshButton = findViewById(R.id.btnRefresh);

//        ... ETC

        refreshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                loadTopPosts();
            }
        });

        createButton.setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick(View v){
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();

                //TODO- ASK USER FOR FILE OR PHOTO
                final File file = new File(imagePath);

                final ParseFile parseFile = new ParseFile(file);

                createPost(description,parseFile, user);
            }
        });


        loadTopPosts();
    }



    private void createPost(String description, ParseFile imageFile, ParseUser user){
        //TODO - create and save post

        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
               if (e == null){
                   Log.d("HomeActivity", "Create Post Success");
               }else{
                   e.printStackTrace();
               }
            }
        });

    }
    private void loadTopPosts(){
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e==null){
                    for (int i =0; i <objects.size(); i++){
                        Log.d("LoginActivity", "Post[" + i + "] = "
                                + objects.get(i).getDescription()
                                + "\nusername = " +objects.get(i).getUser().getUsername());
                    }
                } else{
                    e.printStackTrace();
                }
            }
        });
    }

}
