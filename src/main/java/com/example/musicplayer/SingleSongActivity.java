package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SingleSongActivity extends AppCompatActivity {

    private ImageView image;
    private TextView textName, textSinger, textCurrentDuration, textTotalDuration;
    private SeekBar seekBar;
    private FloatingActionButton btnPlayPause;
    private ImageView btnPrev, btnNext;
    private static ImageView btnFav;
    private static MediaPlayer song;
    private int pausedPosition ;
    private static int position = -1;
    private static String parentActivity;
    private static int currentProgress;
    private static ArrayList<Song> songs;
    private ArrayList<Song> allSongs,likedSongs ;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_song);

        initViews();

        //songs = Utils.getAllSongs();
        allSongs = Utils.getAllSongs();
        likedSongs = Utils.getLikedSongs();

        getSongIntent();
        setViews();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(song != null && fromUser){
                    song.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekBarThread();

        SingleSongActivity.this.runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {
                int totalDuration = song.getDuration()/1000;
                if(song != null) {
                    int currentPosition = song.getCurrentPosition() /1000;
                    seekBar.setProgress(currentPosition);
                    textCurrentDuration.setText(formattedTime(currentPosition));
                    textTotalDuration.setText(formattedTime(totalDuration));
                }
                handler.postDelayed(this,1000);
            }
        });

        //seekBarThread();

        onBtnPlayPauseClick();
        onBtnNextClick();
        onBtnPrevClick();

        //onBtnFavClick(Utils.getInstance(this).getSongById(position+1));
        handleFavButton(Utils.getInstance(this).getSongById(position+1));
    }

    private void handleFavButton(Song playingSong){
        ArrayList<Song> likedSongs = Utils.getLikedSongs();
        boolean exists = false;
        for(Song s : likedSongs){
            if(s.getId() == playingSong.getId()){
                exists = true;
            }
        }

        if(exists){
            btnFav.setImageResource(R.drawable.filled_star);
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(SingleSongActivity.this).removeFromLikedSongs(playingSong)){
                        btnFav.setImageResource(R.drawable.star_border);
                        Toast.makeText(SingleSongActivity.this, "Removed from Liked Songs", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SingleSongActivity.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(SingleSongActivity.this).addToLikedSongs(playingSong)){
                        btnFav.setImageResource(R.drawable.filled_star);
                        Toast.makeText(SingleSongActivity.this, "Added to Liked Songs", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SingleSongActivity.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


//    private void onBtnFavClick(Song playingSong) {
//        ArrayList<Song> likedSongs = Utils.getLikedSongs();
//        btnFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean exists = false;
//                for(Song s : likedSongs){
//                    if(s.getId() == playingSong.getId()){
//                        exists = true;
//                    }
//                }
//
//                if(exists){
//                    if(Utils.getInstance(SingleSongActivity.this).removeFromLikedSongs(playingSong)){
//                        btnFav.setImageResource(R.drawable.star_border);
//                        Toast.makeText(SingleSongActivity.this, "Removed from Liked Songs", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(SingleSongActivity.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    if(Utils.getInstance(SingleSongActivity.this).addToLikedSongs(playingSong)){
//                        btnFav.setImageResource(R.drawable.filled_star);
//                        Toast.makeText(SingleSongActivity.this, "Added to Liked Songs", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(SingleSongActivity.this, "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//
//    }

    private void onBtnPrevClick() {
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song.isPlaying()){
                    song.stop();
                    song.release();
                    if((position-1)<0){
                        position = songs.size() - 1;
                    }else{
                        position = position-1;
                    }
                    song = MediaPlayer.create(SingleSongActivity.this,songs.get(position).getFile());
                    setViews();
                    btnPlayPause.setImageResource(R.drawable.pause_btn);
                    song.start();
                }else {
                    song.stop();
                    song.release();
                    if((position-1)<0){
                        position = songs.size() - 1;
                    }else{
                        position = position-1;
                    }
                    song = MediaPlayer.create(SingleSongActivity.this,songs.get(position).getFile());
                    setViews();
                    btnPlayPause.setImageResource(R.drawable.play_btn);
                }
            }
        });
    }

    private void onBtnNextClick() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(song.isPlaying()){
                            song.stop();
                            song.release();
                            position = (position+1) % songs.size();
                            song = MediaPlayer.create(SingleSongActivity.this,songs.get(position).getFile());
                            setViews();
                            btnPlayPause.setImageResource(R.drawable.pause_btn);
                            song.start();
                        }else{
                            song.stop();
                            song.release();
                            position = (position+1) % songs.size();
                            song = MediaPlayer.create(SingleSongActivity.this,songs.get(position).getFile());
                            setViews();
                            btnPlayPause.setImageResource(R.drawable.play_btn);
                        }
                    }
                });
            }
        });
    }

    private void onBtnPlayPauseClick() {
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(song.isPlaying()){
                            song.pause();
                            pausedPosition = song.getCurrentPosition();
                            btnPlayPause.setImageResource(R.drawable.play_btn);
                        }else{
                            song.seekTo(pausedPosition);
                            song.start();
                            btnPlayPause.setImageResource(R.drawable.pause_btn);
                        }
                    }
                });
            }
        });
    }

    private String formattedTime(int currentPosition) {
        String totalOut = "";
        String totalNew = "";
        String seconds = String.valueOf(currentPosition % 60);
        String minutes = String.valueOf(currentPosition / 60);
        totalOut = minutes + ":" +seconds;
        totalNew = minutes + ":" +"0" + seconds;
        if(seconds.length()==1){
            return totalNew;
        }else {
            return totalOut;
        }
    }

    private void seekBarThread() {
        new Thread(){
            public void run(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(song != null){
                            int currentPosition = song.getCurrentPosition()/1000;
                            seekBar.setProgress(currentPosition);
                            //textCurrentDuration.setText("hello"+song.getCurrentPosition());
                            //textTotalDuration.setText(to);
                            //Log.e("playing : ",""+song.isPlaying());
                        }
                    }
                });
            }
        }.start();
//        runOnUiThread(new Runnable() {
//            int totalDuration = song.getDuration();
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void run() {
//
//                textTotalDuration.setText(""+totalDuration);
//                textCurrentDuration.setText(""+song.getCurrentPosition());
////                while (seekBar.getProgress() <= totalDuration) {
////                    int currentProgress = song.getCurrentPosition();
////                    seekBar.setProgress(currentProgress, false);
////                    textCurrentDuration.setText(""+currentProgress);
////                }
//            }
//        });
    }

    private void getSongIntent() {
        Intent intent = getIntent();
        if(intent != null){
            position = intent.getIntExtra("position",-1);
            parentActivity = intent.getStringExtra("parentActivity");

            //Log.d("1st song",songs.get(0).getName());

            if (parentActivity=="likedSongs"){
                songs = likedSongs;
                Log.v("1st song in liked",songs.get(0).getName());
            }else{
                songs = allSongs;
                Log.v("1st song in all",songs.get(0).getName());
            }

            if(position != -1){
                if(song != null){
                    song.stop();
                    song.release();
                    song = MediaPlayer.create(this,songs.get(position).getFile());
                    song.start();
                }else{
                    song = MediaPlayer.create(this,songs.get(position).getFile());
                    song.start();
                }
                int totalDuration = song.getDuration();
                seekBar.setMax(totalDuration);

                Thread thread = new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        currentProgress = song.getCurrentPosition();
                        seekBar.setProgress(currentProgress, false);
                    }
                });
                thread.start();
            }

        }

    }


//    private void createSong(int position) {
//        //Song incomingSong = Utils.getInstance(this).getSongById(songId);
//        //songs = Utils.getInstance(this).getAllSongs();
//
//        if(song != null){
//            if(song.isPlaying()) {
//                song.stop();
//                song.release();
//                song = MediaPlayer.create(this, songs.get(position).getFile());
//                int totalDuration = song.getDuration();
//                seekBar.setMax(totalDuration);
//                song.start();
//
//                Thread thread = new Thread(new Runnable() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void run() {
//                        while (seekBar.getProgress() <= totalDuration) {
//                            int currentProgress = song.getCurrentPosition();
//                            seekBar.setProgress(currentProgress, false);
//                        }
//                    }
//                });
//                thread.start();
//            }
//        }else{
//                song = MediaPlayer.create(this,songs.get(position).getFile() );
//                int totalDuration = song.getDuration();
//                seekBar.setMax(totalDuration);
//                song.start();
//
//                Thread thread = new Thread(new Runnable() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void run() {
//                        while(seekBar.getProgress()<= totalDuration){
//                            int currentProgress = song.getCurrentPosition();
//                            seekBar.setProgress(currentProgress,false);
//                        }
//                    }
//                });
//                thread.start();
//        }
//    }


    private void setViews() {
        textName.setText(songs.get(position).getName());
        textSinger.setText(songs.get(position).getSinger());

        Glide.with(this)
                .asBitmap()
                .load(songs.get(position).getImageURL())
                .into(image);
    }

    private void initViews() {
        image = findViewById(R.id.image);
        textName = findViewById(R.id.textName);
        textSinger = findViewById(R.id.textSinger);
        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        textCurrentDuration = findViewById(R.id.textCurrentDuration);
        textTotalDuration = findViewById(R.id.textTotalDuration);

        btnFav = findViewById(R.id.btnFav);
    }
}