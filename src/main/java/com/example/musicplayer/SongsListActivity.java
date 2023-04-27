package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {

    private RecyclerView songsRecView;
    //ArrayList<Song> songs = new ArrayList<>();

    //ArrayList<Song> albumSongs = new ArrayList<>();

    TextView textHello;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        songsRecView = findViewById(R.id.songsRecView);
        textHello = findViewById(R.id.textHello);

                SongAdapter adapter = new SongAdapter(this,"allSongs");
                adapter.setSongs(Utils.getAllSongs());

                songsRecView.setAdapter(adapter);
                songsRecView.setLayoutManager(new LinearLayoutManager(this));

    }


}