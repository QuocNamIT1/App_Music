package com.example.nam.music.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.music.MusicService;
import com.example.nam.music.NowAlbumPlaying;
import com.example.nam.music.R;
import com.example.nam.music.infrastructure.Song;

/**
 * Created by nam on 7/16/2016.
 */
public class MediaPlayerFragment extends Fragment implements View.OnClickListener {
    ImageView imageAlbum;
    TextView songName;
    TextView singerName;
    ImageView imagePrev;
    ImageView imagePause;
    ImageView imageNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
        imageAlbum = (ImageView) view.findViewById(R.id.fragment_mediaplayer_imagealbum);
        songName = (TextView) view.findViewById(R.id.fragment_mediaplayer_songname);
        singerName = (TextView) view.findViewById(R.id.fragment_mediaplayer_singername);
        imagePrev = (ImageView) view.findViewById(R.id.fragment_mediaplayer_imagePrev);
        imagePause = (ImageView) view.findViewById(R.id.fragment_mediaplayer_imagePause);
        imageNext = (ImageView) view.findViewById(R.id.fragment_mediaplayer_imageNext);
        imagePrev.setOnClickListener(this);
        imagePause.setOnClickListener(this);
        imageNext.setOnClickListener(this);



        IntentFilter intentFilter = new IntentFilter(NowAlbumPlaying.ACTION_CHANGEFRAGMENT);
        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getStringExtra("ACTION")) {
                    case NowAlbumPlaying.ACTION_PREPARE:
                        if (MusicService.getNowAlbumPlaying().getImageAlbum() != null)
                            imageAlbum.setImageBitmap(MusicService.getNowAlbumPlaying().getImageAlbum());
                        imagePause.setImageResource(R.drawable.uamp_ic_play_arrow_white_24dp);
                        Song song = MusicService.getNowAlbumPlaying().getSongPlaying();
                        songName.setText(song.getName());
                        singerName.setText(song.getSinger());
                        break;
                    case NowAlbumPlaying.ACTION_PLAY:
                        imagePause.setImageResource(R.drawable.uamp_ic_pause_white_24dp);
                        break;

                }
            }
        };
        getActivity().registerReceiver(mReceiver, intentFilter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMediaPlayer();
    }

    public void loadMediaPlayer(){
    if(MusicService.getNowAlbumPlaying().checkMediaPlayer()){
        if(MusicService.getNowAlbumPlaying().isExists()){
            Song song = MusicService.getNowAlbumPlaying().getSongPlaying();
            if (MusicService.getNowAlbumPlaying().getImageAlbum() != null)
                imageAlbum.setImageBitmap(MusicService.getNowAlbumPlaying().getImageAlbum());
            songName.setText(song.getName());
            singerName.setText(song.getSinger());
            imagePause.setImageResource((MusicService.getNowAlbumPlaying().isPlaying()?R.drawable.uamp_ic_pause_white_24dp:R.drawable.uamp_ic_play_arrow_white_24dp));
        }
    }
}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_mediaplayer_imagePrev:
                MusicService.getNowAlbumPlaying().skipToPrev();
                break;
            case R.id.fragment_mediaplayer_imagePause:
                if (MusicService.getNowAlbumPlaying().isPlaying()) {
                    imagePause.setImageResource(R.drawable.uamp_ic_play_arrow_white_24dp);
                    MusicService.getNowAlbumPlaying().pause();
                } else {
                    imagePause.setImageResource(R.drawable.uamp_ic_pause_white_24dp);
                    MusicService.getNowAlbumPlaying().resume();
                }
                break;
            case R.id.fragment_mediaplayer_imageNext:
                MusicService.getNowAlbumPlaying().skipToNext();
                break;
        }
    }
}
