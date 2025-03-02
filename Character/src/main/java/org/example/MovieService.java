package org.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface MovieService {
    Optional<Movie> findById(UUID id);
    List<Movie> findAll();
    void save(Movie movie);
    void delete(UUID id);

    Movie deleteMovieDTO(String movieName);
    List<CharacterPreviewDTO> getCharactersByMovieId(UUID id);
    List<CharacterPreviewDTO> getCharactersByMovieTitle(String title);
    CharacterPreviewDTO addCharacterToMovie(UUID id, CharacterCreateUpdateDTO character);
    CharacterPreviewDTO addCharacterToMovieByTitle(String title, CharacterCreateUpdateDTO character);
    MoviePreviewDTO updateMovieDTO(UUID ID, MovieCreateUpdateDTO movie);
    MoviePreviewDTO updateMovieByName(String title, MovieCreateUpdateDTO movie);
     void createMovie(UUID idMovie, MovieCreateUpdateDTO movieDTO);
}
