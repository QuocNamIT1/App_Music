package com.example.nam.music.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nam.music.MusicService;
import com.example.nam.music.NowAlbumPlaying;
import com.example.nam.music.R;
import com.example.nam.music.infrastructure.FactoryMusic;
import com.example.nam.music.infrastructure.Genre;
import com.example.nam.music.infrastructure.Music;
import com.example.nam.music.view.AlbumAdapter;
import com.example.nam.music.view.Drawer.NavDrawer;
import com.example.nam.music.view.Drawer.NavDrawerItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends BaseAuthenticatedActivity {

    public static Genre GENRE=Genre.THATTINH;

    AlbumAdapter albumAdapter;
    GridView albumsGridView;

    @Override
    protected void createMediaPlayerBar() {
        mediaPlayerBar=findViewById(R.id.fragment_mediaplayer);
        mediaPlayerBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AlbumAcitivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onMusicCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        albumsGridView=(GridView)findViewById(R.id.albums_gv);
        albumsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicService.init();
                MusicService.setAlbum(FactoryMusic.createMusic(GENRE).getAlbum(position));
                Intent service = new Intent(MainActivity.this, MusicService.class);
                service.setAction(NowAlbumPlaying.ACTION_DEFAULT);
                startService(service);
                Intent intent = new Intent(MainActivity.this, AlbumAcitivity.class);

                startActivity(intent);
            }
        });
        NavDrawer drawer=new NavDrawer(this);
        drawer.add(new NavDrawerItem("Thất tình", R.drawable.ic_audio_pause));
        drawer.add(new NavDrawerItem("Rap Việt",R.drawable.ic_back));
        drawer.create();


        albumAdapter=new AlbumAdapter(this,R.layout.item_album_gridview,FactoryMusic.createMusic(Genre.THATTINH).getListAlbums());
        albumsGridView.setAdapter(albumAdapter);
        albumAdapter.notifyDataSetChanged();

        Firebase.setAndroidContext(this);
        Firebase firebase=new Firebase("https://nhac-95db8.firebaseio.com");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (Genre s : Genre.values()) {
                    if (s != Genre.ALL) {
                        Music music = FactoryMusic.createMusic(s);
                        music.loadData(dataSnapshot);
                    }
                }
                changAdapterAlbum();
               // albumAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void changAdapterAlbum() {
        albumAdapter=new AlbumAdapter(this,R.layout.item_album_gridview,FactoryMusic.createMusic(Genre.THATTINH).getListAlbums());
        albumsGridView.setAdapter(albumAdapter);
    }
}
