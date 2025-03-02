package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandRunner implements CommandLineRunner {
    private final MovieService movieService;

    public CommandRunner(MovieService movieService) {

        this.movieService = movieService;
    }

    @Override
    public void run(String... args){
        Scanner scanner = new Scanner(System.in);
        boolean stop=false;

        while(!stop){
            System.out.println("1. Name all movies in data base");
            System.out.println("2. Name all characters in data base");
            System.out.println("3. Add new movie");
            System.out.println("4. Add new character");
            System.out.println("5. Delete movie");
            System.out.println("6. Delete character");
            System.out.println("7. Exit");
            System.out.print("What do you want to do:");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    this.movieService.findAll().stream().forEach(movie -> System.out.println(movie));
                    break;
                case "3":
                    System.out.println("Type the title of the movie:");
                    String nameOfTheMovie= scanner.nextLine();
                    System.out.println("In what year the movie had its premiere:");
                    int yearOfTheMovie=scanner.nextInt();
                    Movie newMovie=Movie.builder()
                            .name(nameOfTheMovie)
                            .yearOfPremiere(yearOfTheMovie)
                            .build();
                    this.movieService.save(newMovie);
                    scanner.nextLine();
                    break;

                case "5":
                    System.out.println("Which movie do you want to delete:");
                    List<Movie> allMoviesThatCanBeDeleted=this.movieService.findAll();
                    for (int idx = 0; idx < allMoviesThatCanBeDeleted.size(); idx++) {
                        System.out.println(idx+1 + ". " + allMoviesThatCanBeDeleted.get(idx));
                    }
                    int indexToDelete=scanner.nextInt();
                    Movie toBeDeleted=allMoviesThatCanBeDeleted.get(indexToDelete-1);
                    UUID movieIdToDelete = toBeDeleted.getMovieID();
                    this.movieService.delete(movieIdToDelete);
                    scanner.nextLine();

                    break;
                case "7":
                    stop = true;
                    break;
                default:
                    System.out.println("wrong command");
            }
        }
        }

    }

