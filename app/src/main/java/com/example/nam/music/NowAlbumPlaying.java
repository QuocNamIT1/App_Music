package com.example.nam.music;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.nam.music.infrastructure.Album;
import com.example.nam.music.infrastructure.Song;

/**
 * Created by nam on 7/19/2016.
 */
public class NowAlbumPlaying {
    public static final String ACTION_CHANGEACTIVITY = "com.example.nam.music.changeacticity";
    public static final String ACTION_CHANGEFRAGMENT = "com.example.nam.music.changefragment";
    public static final String ACTION_DEFAULT = "com.example.nam.music.default";
    public static final String ACTION_PAUSE = "com.example.nam.music.pause";
    public static final String ACTION_PREPARE = "com.example.nam.music.prepare";
    public static final String ACTION_PLAY = "com.example.nam.music.play";
    public static final String ACTION_PREV = "com.example.nam.music.prev";
    public static final String ACTION_NEXT = "com.example.nam.music.next";
    public static final String ACTION_RESUME = "com.example.nam.music.resume";


    private MusicNotification notification;
    private MediaPlayer mediaPlayer;
    private MusicService mService;
    private int posPause = -1;
    private Album album;
    private int posPlayingSong = 0;
    private int MaxDurationSongCurrent = 0;
    private Song songCurrent = null;

    public void init(MusicService service) {
        mediaPlayer = new MediaPlayer();
        mService = service;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    skipToNext();
                    Log.d("ERROR", "Error Link12");
                }
            });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {


                return true;
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                MaxDurationSongCurrent = mediaPlayer.getDuration();
                songCurrent.play(mediaPlayer);
                sendActionActivity(ACTION_PLAY);
                sendActionFragment(ACTION_PLAY);
                notification.setType(MusicNotification.NOTIFICATION_PLAY);
                notification.onNotify();
            }
        });
        notification = new MusicNotification(mService);

    }

    public Song getSongPlaying() {

        return songCurrent;
    }

    private void sendActionActivity(String action) {
        Intent intent = new Intent(ACTION_CHANGEACTIVITY).putExtra("ACTION", action);
        mService.sendBroadcast(intent);
    }
    private void sendActionFragment(String action) {
        Intent intent = new Intent(ACTION_CHANGEFRAGMENT).putExtra("ACTION", action);
        mService.sendBroadcast(intent);
    }
    public Bitmap getImageAlbum(){
        return album.getImageAlbum();
    }
    public void setImageAlbum(Bitmap bitmap){
        album.setImageAlbum(bitmap);
    }
    public void play() {
        if(album!=null) {
            songCurrent = album.getSong(posPlayingSong);
            songCurrent.prepare(mediaPlayer);
            sendActionActivity(ACTION_PREPARE);
            sendActionFragment(ACTION_PREPARE);
            notification.build();
            notification.setType(MusicNotification.NOTIFICATION_PREPARE);
            notification.onNotify();
            notification.startForeground();
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            notification.setType(MusicNotification.NOTIFICATION_PAUSE);
            notification.onNotify();
            sendActionActivity(ACTION_PAUSE);
            sendActionFragment(ACTION_PAUSE);
            posPause = mediaPlayer.getCurrentPosition();
        }
    }

    public void resume() {
        if (mediaPlayer != null && posPause != -1) {
            mediaPlayer.start();
            mediaPlayer.seekTo(posPause);
            posPause = -1;
            notification.setType(MusicNotification.NOTIFICATION_PLAY);
            notification.onNotify();
            sendActionActivity(ACTION_RESUME);
            sendActionFragment(ACTION_RESUME);
        }
    }

    public void skipToNext() {
        posPlayingSong++;
        if (posPlayingSong >= album.getSize()) {
            posPlayingSong = 0;
        }
        play();
        posPause=-1;
    }

    public void skipToPrev() {
        posPlayingSong--;
        if (posPlayingSong < 0) {
            posPlayingSong = album.getSize() - 1;
        }
        play();
        posPause=-1;
    }

    public int getMaxTime() {
        return MaxDurationSongCurrent;
    }

    public int getCurrentDuration() {
        if (mediaPlayer.isPlaying())
            return mediaPlayer.getCurrentPosition();
        return 0;
    }

    public boolean isExists() {
        if (album == null || album.getSize() == 0)
            return false;
        return true;
    }
    public boolean checkMediaPlayer(){
        if(mediaPlayer!=null)
            return true;
        return false;
    }
    public boolean isPlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            return true;
        return false;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setPositionSong(int pos) {
        this.posPlayingSong = pos;
    }
}
