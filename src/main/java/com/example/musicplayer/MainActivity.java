package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView albumsRecView;
    private Button btnGoToSongs;
    private TextView textGreet, textLikedSongs, textAllSongs;
    private ImageView imageAllSongs, imageLikedSongs, imageMenu;
    private CardView cardAllSongs, cardLikedSongs;
    private ArrayList<Song> allSongs,likedSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setGreeting();

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello Sahana", Toast.LENGTH_SHORT).show();
            }
        });

        setCards();

        // to get screen width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;


        AlbumsRecViewAdapter adapter = new AlbumsRecViewAdapter(this,screenWidth);
        adapter.setAlbums(Utils.getInstance(this).getAllAlbums());

        albumsRecView.setAdapter(adapter);
        albumsRecView.setLayoutManager(new GridLayoutManager(this,2)); //spanCount = no.of columns

        btnGoToSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongsListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setCards() {
        //all songs cardview
        allSongs = Utils.getAllSongs();
        if(allSongs != null){
            Glide.with(this).asBitmap().load(allSongs.get(0).getImageURL()).into(imageAllSongs);
        }


        cardAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SongsListActivity.class);
                startActivity(intent);
            }
        });

        //liked songs cardview
        likedSongs = Utils.getLikedSongs();
        if(likedSongs != null){
            Glide.with(this).asBitmap().load(likedSongs.get(0).getImageURL()).into(imageLikedSongs);
            String a = likedSongs.get(0).getImageURL();

        }

        cardLikedSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LikedSongsListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setGreeting() {
        //TODO: set good morning, noon and evening according to time
    }


    private void initViews() {
        albumsRecView = findViewById(R.id.albumsRecView);
        btnGoToSongs = findViewById(R.id.btnGoToSongs);
        textGreet = findViewById(R.id.textGreet);
        imageMenu = findViewById(R.id.imageMenu);

        imageAllSongs = findViewById(R.id.imageAllSongs);
        imageLikedSongs = findViewById(R.id.imageLikedSongs);

        cardAllSongs = findViewById(R.id.cardAllSongs);
        cardLikedSongs = findViewById(R.id.cardLikedSongs);

    }
}