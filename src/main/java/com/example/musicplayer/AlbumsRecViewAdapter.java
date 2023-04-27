package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumsRecViewAdapter extends RecyclerView.Adapter<AlbumsRecViewAdapter.ViewHolder> {

    ArrayList<Album> albums = new ArrayList<>();
    ArrayList<Song> songs;
    private Context mContext;
    private int screenWidth;

    public AlbumsRecViewAdapter(Context mContext, int screenWidth) {
        this.mContext = mContext;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view1);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set content for all UI elements
        holder.textAlbumName.setText(albums.get(position).getAlbumName());

//        Glide.with(mContext)
//                .asBitmap()
//                .load(songs.get(0).getImageURL())
//                .into(holder.imageAlbum);

        holder.parent.setMinimumWidth(screenWidth/2);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SongsInAlbumActivity.class);
                intent.putExtra("singer_name",albums.get(position).getSingerName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textAlbumName;
        private ImageView imageAlbum;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textAlbumName = itemView.findViewById(R.id.textAlbumName);
            imageAlbum = itemView.findViewById(R.id.imageAlbum);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
