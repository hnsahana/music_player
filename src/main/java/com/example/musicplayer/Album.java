package com.example.musicplayer;

import java.util.ArrayList;

public class Album {
    private String albumName;
    private String singerName;
    private ArrayList<Song> songsInAlbum;

    public Album(String albumName, String singerName) {
        this.albumName = albumName;
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public ArrayList<Song> getSongsInAlbum() {
        return songsInAlbum;
    }

    public void setSongsInAlbum(ArrayList<Song> songsInAlbum) {
        this.songsInAlbum = songsInAlbum;
    }
}
