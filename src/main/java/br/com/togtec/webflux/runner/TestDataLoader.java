package br.com.togtec.webflux.runner;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.togtec.webflux.document.Playlist;
import br.com.togtec.webflux.repositories.PlaylistRepository;
import reactor.core.publisher.Flux;

@Component
@Profile("test")
public class TestDataLoader implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(TestDataLoader.class);
    private final PlaylistRepository playlistRepository;

    TestDataLoader(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Playlist playlist1 = new Playlist("64d2f3e3b2d78e0000000001", "API REST Spring Boot");
        Playlist playlist2 = new Playlist("64d2f3e3b2d78e0000000002", "Deploying a Java application on IBM Cloud");
        Playlist playlist3 = new Playlist(UUID.randomUUID().toString(), "Github");
        Playlist playlist4 = new Playlist(UUID.randomUUID().toString(), "Spring Security");
        Playlist playlist5 = new Playlist(UUID.randomUUID().toString(), "Web Service RESTFULL");
        Playlist playlist6 = new Playlist(UUID.randomUUID().toString(), "Bean in Spring Framework");

        playlistRepository.deleteAll()
            .thenMany(
                Flux.just(playlist1, playlist2, playlist3, playlist4, playlist5, playlist6))
                .flatMap(playlistRepository::save)  // asynchronous operation to save each playlist to the database
                .doOnNext(playlist -> logger.info("Saved playlist: " + playlist.getName()))  // prints each saved playlist
                .doOnTerminate(() -> logger.info("Test data loaded into database successfully!")) // action after completion
                .subscribe();  // starts asynchronous execution
    }
    
}