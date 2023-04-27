package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    private Context context;
    private String parentActivity;

    //create a setter for arraylist
    private ArrayList<Song> songs = new ArrayList<>();

    public SongAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view and view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set content for all UI elements
        holder.textSongName.setText(songs.get(position).getName());
        holder.textSingerName.setText(songs.get(position).getSinger());

        Glide.with(context)
                .asBitmap()
                .load(songs.get(position).getImageURL())
                .into(holder.image_item);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleSongActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("parentActivity", parentActivity);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        //return size or length of songs array
        return songs.size();
    }

    //setter for songs array
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }


    //initialize all the UI elements in the list item
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textSongName, textSingerName;
        private ImageView image_item;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textSongName = itemView.findViewById(R.id.textSongName);
            textSingerName = itemView.findViewById(R.id.textSingerName);
            image_item = itemView.findViewById(R.id.image_item);
            parent = itemView.findViewById(R.id.parent);

        }
    }
}
