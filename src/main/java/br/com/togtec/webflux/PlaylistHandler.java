package br.com.togtec.webflux;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.togtec.webflux.document.Playlist;
import br.com.togtec.webflux.services.PlaylistService;
import reactor.core.publisher.Mono;

//@Component
public class PlaylistHandler {
    
    @Autowired
    PlaylistService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromPublisher(playlist.flatMap(service::save), Playlist.class));
    }

}
