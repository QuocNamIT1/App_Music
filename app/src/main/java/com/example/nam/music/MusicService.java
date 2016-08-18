package com.example.nam.music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nam.music.infrastructure.Album;
import com.example.nam.music.infrastructure.MusicApplaication;
import com.example.nam.music.infrastructure.PlayAudio;
import com.example.nam.music.infrastructure.Song;

/**
 * Created by nam on 7/19/2016.
 */
public class MusicService extends Service {

    private static NowAlbumPlaying nowAlbumPlaying=null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
    public static NowAlbumPlaying getNowAlbumPlaying(){
        return (nowAlbumPlaying!=null?nowAlbumPlaying:new NowAlbumPlaying());
    }
    public static void init(){
        nowAlbumPlaying=(nowAlbumPlaying!=null?nowAlbumPlaying:new NowAlbumPlaying());
    }
    public static void setAlbum(Album album){
        nowAlbumPlaying.setAlbum(album);

    }
    @Override
    public void onCreate() {
        super.onCreate();
        nowAlbumPlaying=(nowAlbumPlaying!=null?nowAlbumPlaying:new NowAlbumPlaying());
        nowAlbumPlaying.init(this);

        Log.d("LOG", "Create");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LOG", "Create");
        switch (intent.getAction()) {
            case NowAlbumPlaying.ACTION_NEXT:
                nowAlbumPlaying.skipToNext();
                break;
            case NowAlbumPlaying.ACTION_PREV:
                nowAlbumPlaying.skipToPrev();
                break;
            case NowAlbumPlaying.ACTION_PLAY:
                if(nowAlbumPlaying.isPlaying()){
                    nowAlbumPlaying.pause();
                }
                else
                nowAlbumPlaying.resume();
                break;
            default:
                nowAlbumPlaying.play();
                break;
        }
        Log.d("LOG", "Command");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        onDestroy();
    }
}
