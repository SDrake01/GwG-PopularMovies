package com.stevendrake.moviehub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by calebsdrake on 6/25/2018.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private int reviewsCount = 0;

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int reviewCardId = R.layout.movie_reviews_cards;
        LayoutInflater inflater = LayoutInflater.from(context);
        Boolean attachToParentImmediately = false;

        View view = inflater.inflate(reviewCardId, viewGroup, attachToParentImmediately);

        return new ReviewsViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return MovieData.reviewResults;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {

        final TextView reviewsAuthor;
        final TextView reviewsContent;
        final Context reviewsContext = null;

        public ReviewsViewHolder(Context context, View itemView) {
            super(itemView);

            // Define the reviews author and reviews content text views
            reviewsAuthor = itemView.findViewById(R.id.tv_reviews_cards_author);
            reviewsContent = itemView.findViewById(R.id.tv_reviews_cards_content);
        }

        void bind(int position){
            reviewsAuthor.setText(MovieData.reviewAuthors.indexOf(position));
            reviewsContent.setText(MovieData.reviewContents.indexOf(position));
        }
    }
}
