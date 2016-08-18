package com.example.nam.music.infrastructure;

import java.util.ArrayList;

/**
 * Created by nam on 7/4/2016.
 */
public class FactoryMusic {
    private static ArrayList<Music> musicsList = new ArrayList<>();

    public static Music createMusic(Genre genre) {
        Music music=null;
        if ((music=isExist(genre)) == null) {
            music = new Music(genre);
            musicsList.add(music);
        }
        return music;
    }

    private static Music isExist(Genre genre) {
        for (int i = 0; i < musicsList. size(); i++) {
            if (musicsList.get(i).getGenre() == genre) {
                return musicsList.get(i);
            }
        }
        return null;
    }
}
