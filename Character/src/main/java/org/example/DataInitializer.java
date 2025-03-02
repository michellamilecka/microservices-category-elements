package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer {
    private final MovieService movieService;
    private final CharacterService characterService;
    @Autowired


    public DataInitializer(MovieService movieService, CharacterService characterService) {
        this.movieService = movieService;
        this.characterService = characterService;
    }
    @PostConstruct

    private void createData(){

        Movie movie1 = Movie.builder()
                .name("Harry Potter And The Goblet Of Fire")
                .yearOfPremiere(2005)
                .charactersInMovie(new ArrayList<>())
                .build();

        this.movieService.save(movie1);

        Movie movie2= Movie.builder()
                .name("Twilight")
                .yearOfPremiere(2008)
                .charactersInMovie(new ArrayList<>())
                .build();

        this.movieService.save(movie2);
        Movie movie3=Movie.builder()
                .name("The Hunger Games")
                .yearOfPremiere(2012)
                .charactersInMovie(new ArrayList<>())
                .build();

        this.movieService.save(movie3);

        Movie movie4=Movie.builder()
                .name("Divergent")
                .yearOfPremiere(2014)
                .charactersInMovie(new ArrayList<>())
                .build();

        this.movieService.save(movie4);

        Character character1= Character.builder()
                .name("Harry Potter")
                .nameOfActor("Daniel")
                .lastNameOfActor("Radcliffe")
                .estimatedAge(14)
                .movie(movie1)
                .build();
        movie1.getCharactersInMovie().add(character1);
        this.characterService.save(character1);

        Character character2= Character.builder()
                .name("Hermione Granger")
                .nameOfActor("Emma")
                .lastNameOfActor("Watson")
                .estimatedAge(15)
                .movie(movie1)
                .build();
        movie1.getCharactersInMovie().add(character2);
        this.characterService.save(character2);


        Character character3= Character.builder()
                .name("Bella Swan")
                .nameOfActor("Kristen")
                .lastNameOfActor("Stewart")
                .estimatedAge(17)
                .movie(movie2)
                .build();
        movie2.getCharactersInMovie().add(character3);
        this.characterService.save(character3);


        Character character4= Character.builder()
                .name("Edward Cullen")
                .nameOfActor("Robert")
                .lastNameOfActor("Pattinson")
                .estimatedAge(104)
                .movie(movie2)
                .build();
        movie2.getCharactersInMovie().add(character4);
        this.characterService.save(character4);


        Character character5= Character.builder()
                .name("Katniss Everdeen")
                .nameOfActor("Jennifer")
                .lastNameOfActor("Lawrence")
                .estimatedAge(16)
                .movie(movie3)
                .build();
        movie3.getCharactersInMovie().add(character5);
        this.characterService.save(character5);


        Character character6= Character.builder()
                .name("Peeta Mellark")
                .nameOfActor("Josh")
                .lastNameOfActor("Hutcherson")
                .estimatedAge(16)
                .movie(movie3)
                .build();
        movie3.getCharactersInMovie().add(character6);
        this.characterService.save(character6);


        Character character7= Character.builder()
                .name("Beatrice Prior")
                .nameOfActor("Shailene")
                .lastNameOfActor("Woodley")
                .estimatedAge(16)
                .movie(movie4)
                .build();
        movie4.getCharactersInMovie().add(character7);
        this.characterService.save(character7);


        Character character8= Character.builder()
                .name("Four")
                .nameOfActor("Theo")
                .lastNameOfActor("James")
                .estimatedAge(24)
                .movie(movie4)
                .build();
        movie4.getCharactersInMovie().add(character8);
        this.characterService.save(character8);



    }
}
