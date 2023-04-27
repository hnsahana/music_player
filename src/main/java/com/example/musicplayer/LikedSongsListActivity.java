package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LikedSongsListActivity extends AppCompatActivity {
    private RecyclerView likedSongsRecview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_songs_list);

        likedSongsRecview = findViewById(R.id.likedSongsRecView);

        SongAdapter adapter = new SongAdapter(this, "likedSongs");
        adapter.setSongs(Utils.getLikedSongs());

        likedSongsRecview.setAdapter(adapter);
        likedSongsRecview.setLayoutManager(new LinearLayoutManager(this));
    }
}