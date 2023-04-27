package com.example.musicplayer;

public class Song {
    private int id;
    private String name;
    private int file;
    private String imageURL;
    private String singer;

    public Song(int id, String name, int file, String imageURL, String singer) {
        this.id = id;
        this.name = name;
        this.file = file;
        this.imageURL = imageURL;
        this.singer = singer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
