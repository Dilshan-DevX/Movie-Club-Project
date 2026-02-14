package com.example.movieclub.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

        holder.munuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.munuBtn);
                popupMenu.getMenu().add("Delete");
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    if (menuItem.getTitle().equals("Delete")) {
                        movieLists.remove(position);
                        notifyDataSetChanged();
                    }
                    return true;
                });
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Context Menu").setItems(new String[]{"Remove Movie"},(dialog, i) -> {
                    movieLists.remove(position);
                    notifyDataSetChanged();
                }).show();
                return false;
            }
        });

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
