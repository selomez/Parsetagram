package com.example.selzerai.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.selzerai.parsetagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    InstaAdapter adapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        posts = new ArrayList<Post>();
        adapter = new InstaAdapter(posts);
        rvPosts = (RecyclerView) findViewById(R.id.rvPost);

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(adapter);

        loadTopPosts();

    }

    private void loadTopPosts() {
        // clear out old items before appending new items
        adapter.clear();
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser().addAscendingOrder("createdAt");
        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    Post post = objects.get(i);
                    posts.add(post);
                    adapter.notifyItemInserted(posts.size() - 1);
                    System.out.print(i);
                }
            }
        });
        adapter.addAll(posts);
    }


    public void launchCreate(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}

