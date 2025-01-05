package br.com.togtec.webflux;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

//@Configuration
public class PlaylistRouter {
    
    @Bean
    RouterFunction<ServerResponse> route(PlaylistHandler handler) {
        return RouterFunctions
            .route(GET("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
            .andRoute(GET("/playlist/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getById)
            .andRoute(POST("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::create);
    }

}
