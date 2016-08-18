package com.example.nam.music.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.music.R;
import com.example.nam.music.helper.BitmapHelper;
import com.example.nam.music.infrastructure.Album;

import java.util.ArrayList;

/**
 * Created by nam on 7/4/2016.
 */
public class AlbumAdapter extends ArrayAdapter<Album> {
    private Context context;
    int idLayout;
    ArrayList<Album> albums;
    public AlbumAdapter(Context context, int resource, ArrayList<Album> objects) {
        super(context, resource, objects);
        this.context=context;
        this.idLayout=resource;
        albums=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=((Activity)context).getLayoutInflater().inflate(idLayout,parent,false);
        }
        Album albumPosition=albums.get(position);
        TextView NameAlbum=(TextView)convertView.findViewById(R.id.item_album_gridview_name);
        ImageView imageAlbum=(ImageView)convertView.findViewById(R.id.item_album_gridview_image);
        NameAlbum.setText(albumPosition.getName());
        if(albumPosition.getImageAlbum()==null) {
            new BitmapHelper(context, imageAlbum, albumPosition).execute();
        }else{
            imageAlbum.setImageBitmap(albumPosition.getImageAlbum());
        }
        return convertView;
    }
}
