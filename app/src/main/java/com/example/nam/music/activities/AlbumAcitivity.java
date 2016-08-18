package com.example.nam.music.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nam.music.MusicService;
import com.example.nam.music.NowAlbumPlaying;
import com.example.nam.music.R;
import com.example.nam.music.infrastructure.Genre;
import com.example.nam.music.infrastructure.Song;
import com.example.nam.music.view.Drawer.DrawerArrowDrawable;

/**
 * Created by nam on 7/16/2016.
 */
public class AlbumAcitivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    int position_select;
    Genre GENRE;

    ImageView back_activity;
    ImageView imagePrev;
    ImageView imagePauseAndPlay;
    ImageView imageNext;
    ImageView imageAlbum;
    TextView songName;
    TextView singerName;
    TextView maxDuration;
    TextView currentDuration;
    SeekBar seekBarMedia;
    DrawerArrowDrawable arrowDrawable;
    BroadcastReceiver mReceiver;
    Thread thread;

    @Override
    protected void createMediaPlayerBar() {

    }

    @Override
    protected void onMusicCreate(Bundle saveState) {
        setContentView(R.layout.activity_album);

        imagePrev = (ImageView) findViewById(R.id.activity_album_imagePrev);
        imagePauseAndPlay = (ImageView) findViewById(R.id.activity_album_imagePause);
        imageNext = (ImageView) findViewById(R.id.activity_album_imageNext);
        songName = (TextView) findViewById(R.id.activity_album_songname);
        singerName = (TextView) findViewById(R.id.activity_album_singername);
        imageAlbum = (ImageView) findViewById(R.id.activity_album_imagealbum);
        maxDuration = (TextView) findViewById(R.id.activity_album_maxduration);
        currentDuration = (TextView) findViewById(R.id.activity_album_currentduration);
        seekBarMedia = (SeekBar) findViewById(R.id.activity_album_seekbar);

        imagePrev.setOnClickListener(this);
        imagePauseAndPlay.setOnClickListener(this);
        imageNext.setOnClickListener(this);
        if (MusicService.getNowAlbumPlaying().getImageAlbum() != null)
            imageAlbum.setImageBitmap(MusicService.getNowAlbumPlaying().getImageAlbum());

        seekBarMedia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minute = progress / 60000;
                int second = progress / 1000 - minute * 60;
                currentDuration.setText((minute < 10 ? ("0" + minute) : (minute + "")) + ":" + (second < 10 ? ("0" + second) : (second + "")));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (MusicService.getNowAlbumPlaying().isPlaying())
                        seekBarMedia.setProgress(MusicService.getNowAlbumPlaying().getCurrentDuration());
                    else {

                    }
                }
            }
        });

        Resources resources = getResources();

        arrowDrawable = new DrawerArrowDrawable(resources);
        arrowDrawable.setStrokeColor(resources.getColor(R.color.light_gray));
        arrowDrawable.setFlip(true);
        arrowDrawable.setParameter(1);

        back_activity = (ImageView) findViewById(R.id.drawer_indicator);
        back_activity.setImageDrawable(arrowDrawable);
        back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (MusicService.getNowAlbumPlaying().isExists()) {
            Song song = MusicService.getNowAlbumPlaying().getSongPlaying();
            if (song != null) {
                songName.setText(song.getName());
                singerName.setText(song.getSinger());
                int time = MusicService.getNowAlbumPlaying().getMaxTime() / 1000;
                seekBarMedia.setMax(time * 1000);
                int minute = time / 60;
                int second = time - minute * 60;
                maxDuration.setText((minute < 10 ? ("0" + minute) : (minute + "")) + ":" + second);
                if (MusicService.getNowAlbumPlaying().isPlaying()) {
                    imagePauseAndPlay.setImageResource(R.drawable.ic_audio_pause);
                    if (!thread.isAlive())
                        thread.start();
                } else
                    imagePauseAndPlay.setImageResource(R.drawable.ic_audio_play);
            }
        }
        if (!thread.isAlive())
            thread.start();
        IntentFilter intentFilter = new IntentFilter(NowAlbumPlaying.ACTION_CHANGEACTIVITY);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getStringExtra("ACTION")) {
                    case NowAlbumPlaying.ACTION_PREPARE:
                        imagePauseAndPlay.setImageResource(R.drawable.ic_audio_play);
                        Song song = MusicService.getNowAlbumPlaying().getSongPlaying();
                        songName.setText(song.getName());
                        singerName.setText(song.getSinger());
                        break;
                    case NowAlbumPlaying.ACTION_PLAY:
                        imagePauseAndPlay.setImageResource(R.drawable.ic_audio_pause);
                        int time = MusicService.getNowAlbumPlaying().getMaxTime() / 1000;
                        seekBarMedia.setMax(MusicService.getNowAlbumPlaying().getMaxTime());
                        int minute = time / 60;
                        int second = time - minute * 60;
                        maxDuration.setText((minute < 10 ? ("0" + minute) : (minute + "")) + ":" + second);
                        break;
                    case NowAlbumPlaying.ACTION_PAUSE:
                        imagePauseAndPlay.setImageResource(R.drawable.ic_audio_play);
                        MusicService.getNowAlbumPlaying().pause();
                        break;
                    case NowAlbumPlaying.ACTION_RESUME:
                        imagePauseAndPlay.setImageResource(R.drawable.ic_audio_pause);
                        MusicService.getNowAlbumPlaying().resume();
                        break;
                }
            }
        };
        this.registerReceiver(mReceiver, intentFilter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_album_imagePrev:
                MusicService.getNowAlbumPlaying().skipToPrev();
                break;
            case R.id.activity_album_imagePause:
                if (MusicService.getNowAlbumPlaying().isPlaying()) {
                    imagePauseAndPlay.setImageResource(R.drawable.ic_audio_play);
                    MusicService.getNowAlbumPlaying().pause();
                } else {
                    imagePauseAndPlay.setImageResource(R.drawable.ic_audio_pause);
                    MusicService.getNowAlbumPlaying().resume();
                }
                break;
            case R.id.activity_album_imageNext:
                MusicService.getNowAlbumPlaying().skipToNext();
                break;
        }
    }
}
