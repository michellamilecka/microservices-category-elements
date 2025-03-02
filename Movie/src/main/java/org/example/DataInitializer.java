package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer {
    private final MovieService movieService;
    @Autowired


    public DataInitializer(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostConstruct

    private void createData(){

        Movie movie1 = Movie.builder()
                .name("Harry Potter And The Goblet Of Fire")
                .yearOfPremiere(2005)
                .build();

        this.movieService.save(movie1);

        Movie movie2= Movie.builder()
                .name("Twilight")
                .yearOfPremiere(2008)
                .build();

        this.movieService.save(movie2);
        Movie movie3=Movie.builder()
                .name("The Hunger Games")
                .yearOfPremiere(2012)
                .build();

        this.movieService.save(movie3);

        Movie movie4=Movie.builder()
                .name("Divergent")
                .yearOfPremiere(2014)
                .build();

        this.movieService.save(movie4);



    }
}
