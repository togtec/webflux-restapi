package br.com.togtec.webflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.togtec.webflux.document.Playlist;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {
    
}
