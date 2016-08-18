package com.example.nam.music.helper;

import android.content.Context;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.example.nam.music.MusicService;
import com.example.nam.music.infrastructure.Album;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nam on 7/15/2016.
 */
public class BitmapHelper {
    private ImageView imageView;
    Album album;
    Context context;
    Bitmap bitmap;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public BitmapHelper(Context context, ImageView imageView, Album album) {
        this.imageView = imageView;
        this.album = album;
        this.context = context;
    }

    public void execute() {
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(album.getLinkImageAlbum(), imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
               album.setImageAlbum(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }
}
