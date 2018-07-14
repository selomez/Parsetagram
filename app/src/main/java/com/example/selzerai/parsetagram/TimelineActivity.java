package com.example.selzerai.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public static final int REQUEST_CODE = 1;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        posts = new ArrayList<Post>();
        adapter = new InstaAdapter(posts);
        rvPosts = (RecyclerView) findViewById(R.id.rvPost);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });

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
                for (int i = objects.size() -1 ; i >= 0; i--) {
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

    public void fetchTimelineAsync(int page) {
        // Remember to CLEAR OUT old items before appending in the new ones
        adapter.clear();
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        postsQuery.getQuery(Post.class).findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = objects.size() - 1; i >= 0; --i) {
                        try {
                            Log.d("HomeActivity", "Post(" + i + ") " +
                                    objects.get(i).getDescription() + "\nusername = "
                                    + objects.get(i).getUser().fetchIfNeeded().getUsername()
                            );
                            Post post = objects.get(i);
                            posts.add(post);
                            adapter.notifyItemChanged(posts.size() - 1);

                        } catch (ParseException j) {
                            j.printStackTrace();
                        }
                    }
                } else { e.printStackTrace(); }
            }
        });
        swipeContainer.setRefreshing(false);
    }
}

