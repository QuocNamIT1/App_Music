package com.example.nam.music.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nam.music.MusicService;
import com.example.nam.music.NowAlbumPlaying;
import com.example.nam.music.R;

/**
 * Created by nam on 7/3/2016.
 */
public abstract class BaseAuthenticatedActivity extends BaseActivity {
    View mediaPlayerBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (musicApplaication.getAuth().isbSplashScreen()) {
            startActivity(new Intent(BaseAuthenticatedActivity.this, SplashScreenActivity.class));
            finish();
            return;
        } else {
            onMusicCreate(savedInstanceState);
            createMediaPlayerBar();
        }
    }

    protected abstract void createMediaPlayerBar();
    @Override
    protected void onResume() {
        super.onResume();
        showOrHideMediaPlayerBar();
    }

    private void showOrHideMediaPlayerBar() {
        if (mediaPlayerBar != null)
            if (MusicService.getNowAlbumPlaying().checkMediaPlayer())
                mediaPlayerBar.setVisibility(View.VISIBLE);
            else
                mediaPlayerBar.setVisibility(View.INVISIBLE);
    }

    protected abstract void onMusicCreate(Bundle saveState);
}
