package br.com.togtec.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.togtec.webflux.document.Playlist;
import br.com.togtec.webflux.exception.ResourceNotFoundException;
import br.com.togtec.webflux.repositories.PlaylistRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepository repository;

    @Override
    public Flux<Playlist> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Playlist> findById(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Playlist not found with id: " + id)));
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        return repository.save(playlist);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return findById(id)
            .flatMap(existingPlayList -> repository.deleteById(id));
    }
    
}
