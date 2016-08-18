package com.example.nam.music.infrastructure;

import android.media.*;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by nam on 7/3/2016.
 */
public class Song implements PlayAudio{
    private String Id;
    private String Name;
    private String Musician;
    private String Singer;
    private String Link;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMusician() {
        return Musician;
    }

    public void setMusician(String musician) {
        Musician = musician;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
    public Song() {

    }
    public Song(String id,String name,String singer,String link){
        this.Id=id;
        this.Name=name;
        this.Singer=singer;
        this.Link=link;
    }

    @Override
    public void prepare(MediaPlayer mediaPlayer) {

        mediaPlayer.reset();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(Link);
        } catch (IOException e) {
        }
            mediaPlayer.prepareAsync();
    }

    @Override
    public void play(MediaPlayer mediaPlayer) {

        mediaPlayer.start();
    }

    @Override
    public void pause(MediaPlayer mediaPlayer) {
        mediaPlayer.pause();
    }

    @Override
    public void stop(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
    }

    @Override
    public void seekto(MediaPlayer mediaPlayer, int pos) {
        mediaPlayer.seekTo(pos);
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }
}
