package com.example.movieclub.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieclub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ArrayList<MovieModel> movieList;

    MovieAdapter adapter;
    Uri selectedImage;
    ActivityResultLauncher<Intent> imagePiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this,movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        imagePiker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImage = result.getData().getData();
                    }

                });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMovieDialog();

            }
        });

    }

    private void showAddMovieDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_movie,null);
        EditText movieNameInput = view.findViewById(R.id.movie_name_input);
        Button selectImgBtn = view.findViewById(R.id.selectImgBtn);

        selectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openGallery();
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("Add Movie")
                .setView(view)
                .setPositiveButton("OK",(dialog, i) -> {
                    String name = movieNameInput.getText().toString();
                    if (!name.isEmpty() && selectedImage != null) {
                        movieList.add(new MovieModel(selectedImage,name));
                        adapter.notifyDataSetChanged();
                        selectedImage = null;
                    }


                }).setNegativeButton("Cancel",null)
                .show();



    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePiker.launch(intent);
    }
}