package com.example.movieclub.activity;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieclub.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Context context;
    ArrayList<MovieModel>movieLists;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieLists) {
        this.context = context;
        this.movieLists = movieLists;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View  view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
       return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        MovieModel movie = movieLists.get(position);
        holder.movieName.setText(movie.getName());
        holder.movieImage.setImageURI(movie.getImageUri());
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieName;
        ImageView munuBtn;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImg);
            movieName = itemView.findViewById(R.id.movieName);
            munuBtn = itemView.findViewById(R.id.moreIcon);

        }
    }
}
