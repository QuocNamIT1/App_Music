package com.example.nam.music.infrastructure;

import android.media.*;
import android.media.MediaPlayer;

/**
 * Created by nam on 7/6/2016.
 */
public interface PlayAudio {
    void prepare(MediaPlayer mediaPlayer);
    void play(MediaPlayer mediaPlayer);
    void pause(MediaPlayer mediaPlayer);
    void stop(MediaPlayer mediaPlayer);
    void seekto(MediaPlayer mediaPlayer,int pos);
}
