package com.mvarlamov.Music.data.rest.response;

import com.mvarlamov.Music.data.model.Song;
import com.mvarlamov.Music.data.model.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDataResponse extends Response {
    private User user;
    private List<Song> songs;

    public UserDataResponse(String message, boolean status, User user, List<Song> songs) {
        super(message, status);
        this.user = user;
        this.songs = songs;
    }
}
