package com.example.musicplayer;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Song> allSongs;
    private static ArrayList<Song> likedSongs;
    private static ArrayList<Album> allAlbums;
    //private static LinkedList<Song> allAlbums ;

    private static ArrayList<Song> albumSongs;

    private Utils(Context context){
        if (allSongs == null){
            allSongs = new ArrayList<>();
            initData();
        }

        if(allAlbums == null){
            allAlbums = new ArrayList<>();
            initData1();
        }

        if(likedSongs == null){
            likedSongs = new ArrayList<>();
        }

        //getSongsByAlbum();
    }

    public ArrayList<Song> getSongsByAlbum(String singerName) {
        ArrayList<Song> songs = allSongs;
        ArrayList<Song> albumSongs = new ArrayList<>();

        if (songs != null) {
            for (Song s : songs){
                if(s.getSinger() == singerName){
                    albumSongs.add(s);
                    Log.d("song name",s.getName());
                }
            }
            return albumSongs;
        }

        return null;
    }

//    public ArrayList<Song> getSongsInAlbum(String singerName){
//        setSongsByAlbum(singerName);
//        for(Album a : allAlbums){
//            if ()
//        }
//    }

    public ArrayList<Album> getAllAlbums() {
        return allAlbums;
    }

    private void initData1() {
        allAlbums.add(new Album("Taylor Swift Mix","Taylor Swift"));
        allAlbums.add(new Album("BTS","BTS"));
        allAlbums.add(new Album("Glass Animals","Glass Animals"));
        allAlbums.add(new Album("Hindi","Pritam"));
    }

    private void initData() {
        allSongs.add(new Song(1,"Heat Waves",R.raw.heat_waves,"https://tse3.mm.bing.net/th?id=OIP.csQhGlx7PHk9UneeJOx4XgHaHa&pid=Api&P=0.jpg","Glass Animals"));
        allSongs.add(new Song(2,"Bad Blood",R.raw.bad_blood_ts,"https://tse2.mm.bing.net/th?id=OIP.-6pydC8EbAySc6foQ7yuYAHaHa&pid=Api&P=0.jpg","Taylor Swift"));
        allSongs.add(new Song(3,"Bad Decisions",R.raw.bad_decisions_bts,"https://tse1.mm.bing.net/th?id=OIP.ipRP1QtDQVwx9BRO3l_YOwHaHa&pid=Api&P=0.jpg","BTS, Snoop Dog, Benny Blanco"));
        allSongs.add(new Song(4,"Karma",R.raw.karma_ts,"https://tse3.mm.bing.net/th?id=OIP.ZYC8vcpuTK0mimWSUnG5hgHaHa&pid=Api&P=0.jpg","Taylor Swift"));
        allSongs.add(new Song(5,"Blood Sweat & Tears",R.raw.blood_sweat_tears_bts,"https://tse2.mm.bing.net/th?id=OIP.vIroURZvSXTqEmvUmU63owHaHa&pid=Api&P=0.jpg","BTS"));
        allSongs.add(new Song(6,"Look What You Made Me Do",R.raw.look_what_you_made_me_do,"https://tse3.mm.bing.net/th?id=OIP.vbab8-bQwYNlc3ziLnB1vgHaGr&pid=Api&P=0.jpg","Taylor Swift"));
        allSongs.add(new Song(7,"Fake Love",R.raw.fake_love_bts,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzBiMrAov4SYKkSH4bYXXpPBdrN7X4ZUD1rg&usqp=CAU.jpg","BTS"));
        allSongs.add(new Song(8,"Mic Drop",R.raw.mic_drop_bts,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5YP_XJLnSzxZJXo0hYFWnMwOhXBsZ8Qqp5Q&usqp=CAU.jpg","BTS"));
        allSongs.add(new Song(9,"Show Me The Thumka",R.raw.tumka,"https://tse4.mm.bing.net/th?id=OIP.1LOfUyQwqhOQw1uRhIRWpAHaEj&pid=Api&P=0.jpg","Pritam"));
    }


    public Song getSongById(int id){
        ArrayList<Song> songs = allSongs;
        if (songs != null) {
            for(Song s: songs){
                if(s.getId()==id){
                    return s;
                }
            }
        }
        return null;
    }

    public Song getSongBySinger(int id){
        ArrayList<Song> songs = allSongs;
        if (songs != null) {
            for(Song s: songs){
                if(s.getId()==id){
                    return s;
                }
            }
        }
        return null;
    }

    public static Utils getInstance(Context context){
        if(instance != null){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }

    public static ArrayList<Song> getAllSongs() {
        return allSongs;
    }

    public static ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    public boolean addToLikedSongs(Song song){
        return likedSongs.add(song);
    }

    public boolean removeFromLikedSongs(Song song){
        return likedSongs.remove(song);
    }
}
