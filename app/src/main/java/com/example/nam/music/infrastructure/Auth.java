package com.example.nam.music.infrastructure;

/**
 * Created by nam on 7/3/2016.
 */
public class Auth {
    private User user;
    private boolean bSplashScreen;
    private boolean bPlayingMedia;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Auth(){
        this.bPlayingMedia = false;
        this.user=new User();
        this.bSplashScreen=false;
    }

    public boolean isbSplashScreen() {
        return bSplashScreen;
    }

    public void setbSplashScreen(boolean bSplashScreen) {
        this.bSplashScreen = bSplashScreen;
    }

    public boolean isPlayingMedia() {
        return bPlayingMedia;
    }

    public void setIsPlayingMedia(boolean bPlayingMedia) {
        this.bPlayingMedia = bPlayingMedia;
    }
}
