package com.stevendrake.moviehub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stevendrake.moviehub.Database.Review;

import java.util.List;

/**
 * Created by calebsdrake on 6/25/2018.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    class ReviewViewHolder extends RecyclerView.ViewHolder{
        private final TextView reviewAuthor;
        private final TextView reviewContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            reviewAuthor = itemView.findViewById(R.id.tv_reviews_cards_author);
            reviewContent = itemView.findViewById(R.id.tv_reviews_cards_content);
        }
    }

    private final LayoutInflater revInflater;
    public static List<Review> showReviews;

    ReviewsAdapter(Context context){
        revInflater = LayoutInflater.from(context);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = revInflater.inflate(R.layout.movie_reviews_cards, viewGroup, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        if (showReviews != null) {
            Review current = showReviews.get(position);
            holder.reviewAuthor.setText(current.getAuthor());
            holder.reviewContent.setText(current.getContents());
        }else {
            holder.reviewAuthor.setText("No Reviews");
            holder.reviewContent.setText("To Display");
        }
    }

    void setReviews(List<Review> reviews){
        showReviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (showReviews == null){
            return 0;
        } else {
            return showReviews.size();
        }
    }
}
