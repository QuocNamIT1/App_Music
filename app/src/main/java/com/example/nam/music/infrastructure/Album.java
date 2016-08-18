package com.example.nam.music.infrastructure;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.*;
import android.media.MediaPlayer;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nam on 7/3/2016.
 */
public class Album implements PlayAudio,Serializable{
    private String Name;
    private String linkImageAlbum;
    private Bitmap imageAlbum=null;
    private ArrayList<Song> songList;
    public Album(String name,String linkImageAlbum){
        this.Name=name;
        songList=new ArrayList<Song>();
        this.linkImageAlbum=linkImageAlbum;
    }
    public void addSong(Song song){
        songList.add(song);
    }
    public int getSize(){
        return songList.size();
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLinkImageAlbum() {
        return linkImageAlbum;
    }

    public void setLinkImageAlbum(String linkImageAlbum) {
        this.linkImageAlbum = linkImageAlbum;
    }
    public Song getSong(int pos){
        return songList.get(pos);
    }

    @Override
    public void prepare(MediaPlayer mediaPlayer) {

    }

    @Override
    public void play(MediaPlayer mediaPlayer) {

    }

    @Override
    public void pause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void stop(MediaPlayer mediaPlayer) {

    }

    @Override
    public void seekto(MediaPlayer mediaPlayer, int pos) {

    }

    public Bitmap getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(Bitmap imageAlbum) {
        this.imageAlbum = imageAlbum;
    }
}
