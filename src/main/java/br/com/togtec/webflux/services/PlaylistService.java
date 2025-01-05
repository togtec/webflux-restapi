package br.com.togtec.webflux.services;

import br.com.togtec.webflux.document.Playlist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {
    Flux<Playlist> findAll();
    Mono<Playlist> findById(String id);
    Mono<Playlist> save(Playlist playlist);
    Mono<Void> deleteById(String id);
}
