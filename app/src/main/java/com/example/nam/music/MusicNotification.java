package com.example.nam.music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.example.nam.music.activities.AlbumAcitivity;
import com.example.nam.music.infrastructure.Music;

/**
 * Created by nam on 7/19/2016.
 */
public class MusicNotification{
    public static final int NOTIFICATION_PLAY=111;
    public static final int NOTIFICATION_PAUSE=112;
    public static final int NOTIFICATION_PREPARE=113;
    public  static final int NOTICATION_ID=1;
    private MusicService mService=null;
    private boolean bStart=false;
    Notification notification=null;
    RemoteViews view;
    RemoteViews bigView;
    public void build(){
        view=new RemoteViews(mService.getPackageName(),R.layout.notification_mediaplayer);
        bigView=new RemoteViews(mService.getPackageName(),R.layout.notification_mediaplayer_expanded);

        if (MusicService.getNowAlbumPlaying().getImageAlbum() != null) {
            view.setImageViewBitmap(R.id.notification_mediaplayer_imagealbum, MusicService.getNowAlbumPlaying().getImageAlbum());
            bigView.setImageViewBitmap(R.id.notification_mediaplayer_expanded_imagealbum, MusicService.getNowAlbumPlaying().getImageAlbum());
        }

        view.setTextViewText(R.id.notification_mediaplayer_songname, MusicService.getNowAlbumPlaying().getSongPlaying().getName());
        bigView.setTextViewText(R.id.notification_mediaplayer_expanded_songname, MusicService.getNowAlbumPlaying().getSongPlaying().getName());

        view.setTextViewText(R.id.notification_mediaplayer_singername, MusicService.getNowAlbumPlaying().getSongPlaying().getSinger());
        bigView.setTextViewText(R.id.notification_mediaplayer_expanded_singername, MusicService.getNowAlbumPlaying().getSongPlaying().getSinger());

        Notification.Builder builder = new Notification.Builder(mService);
        builder.setSmallIcon(R.drawable.ic_notification).setContentTitle("My music").setTicker("Playing").setContent(view).setWhen(System.currentTimeMillis());


        Intent previousIntent = new Intent(mService, MusicService.class);
        previousIntent.setAction(NowAlbumPlaying.ACTION_PREV);
        PendingIntent ppreviousIntent = PendingIntent.getService(mService, 0,
                previousIntent, 0);
        bigView.setOnClickPendingIntent(R.id.notification_mediaplayer_expanded_imagePrev, ppreviousIntent);

        Intent playIntent = new Intent(mService, MusicService.class);
        playIntent.setAction(NowAlbumPlaying.ACTION_PLAY);
        PendingIntent pplayIntent = PendingIntent.getService(mService, 0,
                playIntent, 0);

        view.setOnClickPendingIntent(R.id.notification_mediaplayer_imagePause,pplayIntent);
        bigView.setOnClickPendingIntent(R.id.notification_mediaplayer_expanded_imagePause,pplayIntent);

        Intent nextIntent = new Intent(mService, MusicService.class);
        nextIntent.setAction(NowAlbumPlaying.ACTION_NEXT);
        PendingIntent pnextIntent = PendingIntent.getService(mService, 0,
                nextIntent, 0);

        view.setOnClickPendingIntent(R.id.notification_mediaplayer_imageNext,pnextIntent);
        bigView.setOnClickPendingIntent(R.id.notification_mediaplayer_expanded_imageNext,pnextIntent);
//        Intent closeIntent = new Intent(mService, MusicService.class);
//        closeIntent.setAction();
//        PendingIntent pcloseIntent = PendingIntent.getService(mService, 0,
//                closeIntent, 0);

        Intent startIntentService = new Intent(mService, AlbumAcitivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mService, 111, startIntentService, 0);
        builder.setContentIntent(contentIntent);

        notification=builder.build();
        notification.bigContentView=bigView;

    }

    public MusicNotification(MusicService service){
        this.mService=service;
    }
    public void setType(int type){
        switch(type){
            case NOTIFICATION_PREPARE:
                view.setImageViewResource(R.id.notification_mediaplayer_imagePause, R.drawable.uamp_ic_play_arrow_white_24dp);
                bigView.setImageViewResource(R.id.notification_mediaplayer_expanded_imagePause, R.drawable.uamp_ic_play_arrow_white_24dp);
                break;
            case NOTIFICATION_PLAY:
                view.setImageViewResource(R.id.notification_mediaplayer_imagePause, R.drawable.uamp_ic_pause_white_24dp);
                bigView.setImageViewResource(R.id.notification_mediaplayer_expanded_imagePause, R.drawable.uamp_ic_pause_white_24dp);
                break;
            case NOTIFICATION_PAUSE:
                view.setImageViewResource(R.id.notification_mediaplayer_imagePause, R.drawable.uamp_ic_play_arrow_white_24dp);
                bigView.setImageViewResource(R.id.notification_mediaplayer_expanded_imagePause, R.drawable.uamp_ic_play_arrow_white_24dp);
                break;
        }
    }
    public void startForeground() {
        if (notification != null&&!bStart)
            mService.startForeground(NOTICATION_ID, notification);

    }

    public void onNotify(){

        NotificationManager notificationManager = (NotificationManager)mService.getSystemService( Context.NOTIFICATION_SERVICE );
        notificationManager.notify(NOTICATION_ID,notification);
    }
}
