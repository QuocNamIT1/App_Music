package com.example.nam.music.infrastructure;

import android.app.Application;

/**
 * Created by nam on 7/3/2016.
 */
public class MusicApplaication extends Application {
    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth=new Auth();
    }

    public Auth getAuth() {
        return auth;
    }
}
