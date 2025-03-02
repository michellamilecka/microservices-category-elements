package org.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface MovieService {
    Optional<Movie> findById(UUID id);
    List<Movie> findAll();
    Movie save(Movie movie);
    void delete(UUID id);
    MoviePreviewDTO createMovieDTO(MovieCreateUpdateDTO movieDTO);
    List<MoviePreviewDTO> getAllMoviesDTO();
    MovieReadDTO getMovieByIdDTO(UUID ID);
    MoviePreviewDTO updateMovieDTO(UUID ID, MovieCreateUpdateDTO movie);
    Movie deleteMovieDTO(String movieName);
}
