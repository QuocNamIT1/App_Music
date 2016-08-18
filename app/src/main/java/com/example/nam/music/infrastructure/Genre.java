package com.example.nam.music.infrastructure;

import java.io.Serializable;

/**
 * Created by nam on 7/3/2016.
 */
public enum Genre implements Serializable {
    ALL("tất cả"),
    THATTINH("thattinh");
    private String value;

    private Genre(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
