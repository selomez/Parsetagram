package com.example.selzerai.parsetagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.selzerai.parsetagram.model.Post;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class InstaAdapter extends RecyclerView.Adapter <InstaAdapter.ViewHolder>{


    private  List<Post> mPosts;
    static Context context;

    public InstaAdapter (List<Post>posts){
        mPosts = posts;
    }

    @NonNull
    @Override
    public InstaAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final Post post = mPosts.get(position);

        holder.tvHandle.setText(post.getUser().getUsername());
    //    holder.tvDescription.setText(post.getDescription());
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, DetailsActivity.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public Post getItem(int position) {
        if (getItemCount() == 0) return null;
        return mPosts.get(position);
    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Post> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView ivImage;
        public TextView tvHandle;
        public TextView tvDescription;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivPicture);
            tvDescription = itemView.findViewById(R.id.etDescription);
            tvHandle = itemView.findViewById(R.id.tvHandle);

           tvHandle.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent (context, ProfileActivity.class);
                   context.startActivity(intent);
               }
           });

        }
    }

}




