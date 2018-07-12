package com.example.selzerai.parsetagram;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.selzerai.parsetagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public class InstaAdapter extends RecyclerView.Adapter <InstaAdapter.ViewHolder>{


    private static List<Post> mPosts;
    static Context context;
    public InstaAdapter (List<Post>posts){ mPosts = posts; }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //TODO
        View postView = inflater.inflate(R.layout.item_post, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        Post post = mPosts.get(position);

        try {
            holder.tvHandle.setText(post.getUser().fetchIfNeeded().getUsername());
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        holder.tvDescription.setText(post.getDescription());

        try {
            final String username = post.getUser().fetchIfNeeded().getUsername();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }

        //Glide.with(context)
        //  .load(post.profileImageUrl);
        //  .into(holder.ivProfileImage);

    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void addAll(ArrayList<Post> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivImage;
        public TextView tvHandle;
        public TextView tvDescription;

        public ViewHolder (View itemView){
            super(itemView);

            ivImage = (ImageView )itemView.findViewById(R.id.picView);
            tvDescription = (TextView ) itemView.findViewById(R.id.etDescription);
            tvHandle = (TextView) itemView.findViewById(R.id.tvHandle);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick (View view){
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION){
                Post post = mPosts.get(position);
                //TODO: CREATE AND SEND TO DETAILS ACTIVITY
                //  Intent intent = new Intent (context, DetailsActivity.class);
                //    intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(tweet));
                //context.startActivity(intent);
           }
        }

    }

//    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
//    public static String getRelativeTimeAgo(String rawJsonDate) {
//        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZ yyyy";
//        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
//        sf.setLenient(true);
//
//        String relativeDate = "";
//        try {
//            long dateMillis = sf.parse(rawJsonDate).getTime();
//            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return relativeDate;
//    }
}



