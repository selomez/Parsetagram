package com.example.selzerai.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.selzerai.parsetagram.model.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    Post post;
  //  @BindView(R.id.tvUsername) public TextView tvUsername;
    @BindView(R.id.tvUsername2) public TextView tvUsername2;
    @BindView(R.id.ivImage) public ImageView ivImage;
    @BindView(R.id.tvDescription) public TextView tvDescription;
    @BindView(R.id.tvTime) public TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvUsername2.setText("@" + post.getUser().getUsername().toString());
        tvDescription.setText(post.getDescription().toString());

        String time = post.getCreatedAt().toString().substring(0, 11);
        tvTime.setText("Created on " + time);

        Glide.with(this)
                .load(post.getImage().getUrl())
                .into(ivImage);

        ParseFile profileImage = post.getUser().getParseFile("profilePic");
        if (profileImage != null) {
            Glide.with(this)
                    .load(profileImage.getUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivImage);
        }
    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void launchCreate(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
    public void launchTimeline(View view) {
        Intent intent = new Intent(this, TimelineActivity.class);
        startActivity(intent);
    }
}



