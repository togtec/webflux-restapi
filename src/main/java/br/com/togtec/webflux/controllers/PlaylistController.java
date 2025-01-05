package br.com.togtec.webflux.controllers;

import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.togtec.webflux.document.Playlist;
import br.com.togtec.webflux.services.PlaylistService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;


@RestController
public class PlaylistController {

    @Autowired
    PlaylistService service;

    @GetMapping("/playlist")
    public Mono<ResponseEntity<Flux<Playlist>>> getAll() {
        Flux<Playlist> playlists = service.findAll();
        return Mono.just(ResponseEntity.ok().body(playlists));
    }
    
    @GetMapping("/playlist/{id}")
    public Mono<ResponseEntity<Playlist>> getById(@PathVariable String id) {
        return service.findById(id)
            .map(ResponseEntity::ok);
    }

    @PostMapping("/playlist")
    public Mono<ResponseEntity<Playlist>> create(@RequestBody Playlist playlist) {
        return service.save(playlist)
                .map(savedPlaylist -> ResponseEntity
                .created(URI.create("/playlist/" + savedPlaylist.getId()))  // returns 201 and the header Location
                .body(savedPlaylist));
    }

    @DeleteMapping("/playlist/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteById(id)
            .then(Mono.just(ResponseEntity.noContent().build()));  // returns 204 No Content
    }

    @GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = service.findAll();
        System.out.print("Events passed here");
        return Flux.zip(interval, events);
    }

}
