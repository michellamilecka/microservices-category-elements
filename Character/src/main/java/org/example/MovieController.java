package org.example;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/{idMovie}")
    public ResponseEntity<Void> handleMovieCreation(@PathVariable UUID idMovie, @RequestBody MovieCreateUpdateDTO movieDTO) {

        movieService.createMovie(idMovie,movieDTO);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{movieName}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieName) {
        Movie movie=movieService.deleteMovieDTO(movieName);
        if (movie!= null){
            return  ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build() ;
        }
    }
    @PutMapping("/{movieName}")
    public ResponseEntity<MoviePreviewDTO> updateMovie(@PathVariable String movieName, @RequestBody MovieCreateUpdateDTO movieDTO) {

        MoviePreviewDTO movie = movieService.updateMovieByName(movieName, movieDTO);

        if (movie != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{title}/characters")
    public ResponseEntity<List<CharacterPreviewDTO>> getCharactersByMovieTitle(@PathVariable String title) {
        List<CharacterPreviewDTO> characters = movieService.getCharactersByMovieTitle(title);
        if (characters == null) {
            return ResponseEntity.notFound().build();
        }
        if (characters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(characters);
    }

    @PostMapping("/{title}/characters")
    public ResponseEntity<CharacterPreviewDTO> addCharacterToMovie(@PathVariable String title, @RequestBody CharacterCreateUpdateDTO characterDTO) {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        CharacterPreviewDTO newCharacter = movieService.addCharacterToMovieByTitle(decodedTitle, characterDTO);

        if (newCharacter == null) {
            return ResponseEntity.notFound().build();
        }

        URI location = UriComponentsBuilder.fromPath("/api/movies/{title}/characters/{id}")
                .buildAndExpand(title, newCharacter.getCharacterID())
                .toUri();


        return ResponseEntity.created(location).body(newCharacter);
    }


}
