package com.example.nam.music.view.Drawer;

import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.nam.music.R;
import com.example.nam.music.activities.BaseActivity;

import java.util.ArrayList;

/**
 * Created by nam on 8/16/2016.
 */
public class NavDrawer {
    private ArrayList<NavDrawerItem> listItemDrawer;
    private BaseActivity activity=null;
    private DrawerLayout drawerLayout;
    DrawerArrowDrawable arrowDrawable;
    ImageView drawer_indicator;
    LinearLayout listViewItemNavDrawer;
    LayoutInflater inflater;
    ViewGroup viewGroup;

    public NavDrawer(BaseActivity activity){
        this.activity=activity;
        listItemDrawer=new ArrayList<NavDrawerItem>();
        this.drawerLayout=(DrawerLayout)activity.findViewById(R.id.drawer_layout);
        viewGroup=(ViewGroup)activity.findViewById(R.id.nav_drawer);
        listViewItemNavDrawer=(LinearLayout)activity.findViewById(R.id.include_main_nav_drawer_listitem);
        inflater=activity.getLayoutInflater();
    }
    public void create(){
        drawer_indicator=(ImageView)activity.findViewById(R.id.drawer_indicator);

        Resources resources=activity.getResources();
        arrowDrawable=new DrawerArrowDrawable(resources);
        arrowDrawable.setStrokeColor(resources.getColor(R.color.light_gray));
        drawer_indicator.setImageDrawable(arrowDrawable);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset >= .995) {

                    arrowDrawable.setFlip(true);
                } else if (slideOffset <= .005) {

                    arrowDrawable.setFlip(false);
                }

                arrowDrawable.setParameter(slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawer_indicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        for(NavDrawerItem item :listItemDrawer){
            listViewItemNavDrawer.addView(item.inflate(inflater,viewGroup));
        }
    }
    public void add(NavDrawerItem item){
        listItemDrawer.add(item);
    }


}
