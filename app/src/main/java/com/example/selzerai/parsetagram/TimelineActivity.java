package com.example.selzerai.parsetagram;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        posts = new ArrayList<>();
        adapter = new InstaAdapter(posts);
       // client = ParseApp
        rvPosts = (RecyclerView) findViewById(R.id.rvPost);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(linearLayoutManager);
        rvPosts.setAdapter(adapter);


            loadTopPosts();
    }

    private void loadTopPosts() {
        // clear out old items before appending new items
        adapter.clear();
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    Post post = objects.get(i);
                    posts.add(post);
                    adapter.notifyItemInserted(posts.size() - 1);
                }
            }
        });
        adapter.addAll(posts);
    }
        private void fetchTimelineAsync(){
            adapter.clear();
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
            swipeContainer.setRefreshing(false);
        }



}

