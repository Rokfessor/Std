package com.mvarlamov.Music.data.rest;

import com.mvarlamov.Music.data.dao.ArtistDAO;
import com.mvarlamov.Music.data.model.Artist;
import com.mvarlamov.Music.data.rest.response.ArtistResponse;
import com.mvarlamov.Music.data.rest.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@PreAuthorize("hasAuthority('ANON')")
public class CommonRest {
    @Autowired
    ArtistDAO artistDAO;

    @GetMapping("/artist/{id}")
    public ResponseEntity<? extends Response> getArtist(@PathVariable int id) {
        Artist artist = artistDAO.getById(id);
        if (artist == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Response.of("Artist with id=" + id + " not found", false));

        return ResponseEntity
                .ok(new ArtistResponse("Artist info", true, Stream.of(artist).collect(Collectors.toList())));
    }

    @GetMapping("/artists")
    public ResponseEntity<? extends Response> getArtists() {
        List<Artist> artistList = artistDAO.getAll();
        return ResponseEntity.ok(new ArtistResponse("List of artists", true, artistList));
    }
}
