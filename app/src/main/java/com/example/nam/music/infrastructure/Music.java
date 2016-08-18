package com.example.nam.music.infrastructure;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;


/**
 * Created by nam on 7/4/2016.
 */
public class Music {
    private static Music INSTANCE=null;

    private Genre genre;
    private ArrayList<Album> albums;

    public Music(Genre genre){
        this.genre=genre;
        albums=new ArrayList<Album>();
    }
    public ArrayList<Album> getListAlbums() {
        return albums;
    }

    public void addAlbum(Album album){
        albums.add(album);
    }
    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
    public Album getAlbum(int pos){
        return albums.get(pos);
    }
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void loadData(DataSnapshot dataSnapshot){
        int counterAlbum= Integer.parseInt(dataSnapshot.child(genre.getValue()).child("Soluong").getValue().toString());
        albums=new ArrayList<Album>();
        for(int i=0;i<counterAlbum;i++){
            DataSnapshot snapshotAlbum=dataSnapshot.child(genre.getValue()).child("Album"+(i+1));
            int counterSong=Integer.parseInt(snapshotAlbum.child("Soluong").getValue().toString());
            Album album=new Album(snapshotAlbum.child("Name").getValue().toString(),snapshotAlbum.child("LinkImage").getValue().toString());
            for(int j=0;j<counterSong;j++){
                DataSnapshot snapshotSong=snapshotAlbum.child("Bai" + (j + 1));
                Song song=new Song(j+1+"",snapshotSong.child("Name").getValue().toString(),snapshotSong.child("Casi").getValue().toString(),snapshotSong.child("Link").getValue().toString());
                album.addSong(song);
            }
            albums.add(album);
        }
    }
}
