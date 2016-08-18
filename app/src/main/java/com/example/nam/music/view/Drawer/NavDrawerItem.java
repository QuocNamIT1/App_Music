package com.example.nam.music.view.Drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.music.R;

/**
 * Created by nam on 8/16/2016.
 */
public class NavDrawerItem {
    private String title="";
    private int icon;
    public NavDrawerItem(String title,int icon){
        this.title=title;
        this.icon=icon;
    }
    public View inflate(LayoutInflater inflater,ViewGroup viewGroup){
        View view= inflater.inflate(R.layout.item_nav_drawer,viewGroup,false);
        TextView titleGenre=(TextView)view.findViewById(R.id.item_nav_drawer_text);
        ImageView iconGenre=(ImageView)view.findViewById(R.id.item_nav_drawer_icon);
        titleGenre.setText(title);
        iconGenre.setImageResource(icon);
        return view;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
