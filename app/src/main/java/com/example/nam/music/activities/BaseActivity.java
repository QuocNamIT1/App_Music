package com.example.nam.music.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.nam.music.NowAlbumPlaying;
import com.example.nam.music.R;
import com.example.nam.music.infrastructure.MusicApplaication;

/**
 * Created by nam on 7/3/2016.
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected MusicApplaication musicApplaication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicApplaication=(MusicApplaication)getApplication();
    }
}
