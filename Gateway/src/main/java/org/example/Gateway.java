package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gateway {

    private final String movieServiceURL;
    private final String characterServiceURL;

    public Gateway(
            @Value("${services.movie-service.url}") String movieServiceURL,
            @Value("${services.character-service.url}") String characterServiceURL) {
        this.movieServiceURL = movieServiceURL;
        this.characterServiceURL = characterServiceURL;
    }

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("characters-movie-route", route -> route
                        .path("/api/movies/{movieId}/characters")
                        .uri(characterServiceURL))


                .route("characters-all-route", route -> route
                        .path("/api/characters/**")
                        .uri(characterServiceURL))


                .route("movies", route -> route
                        .path("/api/movies/**")
                        .uri(movieServiceURL))
                .build();
    }

}
