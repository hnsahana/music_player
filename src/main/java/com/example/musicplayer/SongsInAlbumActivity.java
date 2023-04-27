package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SongsInAlbumActivity extends AppCompatActivity {

    private RecyclerView songsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_in_album);

        songsRecView = findViewById(R.id.songsRecView);

        Intent intent = getIntent();
        if(intent != null) {
            String singer = intent.getStringExtra("singer_name");
            if (singer != null) {
                SongAdapter adapter = new SongAdapter(this,"");
                adapter.setSongs(Utils.getInstance(this).getSongsByAlbum(singer));

                songsRecView.setAdapter(adapter);
                songsRecView.setLayoutManager(new LinearLayoutManager(this));


            }
        }
    }
}