package org.example;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final RestTemplate restTemplate;

    public MovieController(MovieService movieService, RestTemplate restTemplate) {
        this.movieService = movieService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<MoviePreviewDTO> createMovie(@RequestBody MovieCreateUpdateDTO movieDTO) {
        MoviePreviewDTO createdMovie = movieService.createMovieDTO(movieDTO);

        UUID movieId = createdMovie.getMovieID();
        String url = "http://localhost:8081/api/movies/" + movieId;

        restTemplate.postForObject(url, movieDTO, Void.class);
        return ResponseEntity.created(URI.create("/api/movies/" + movieId)).body(createdMovie);
    }

    @GetMapping
    public ResponseEntity<List<MoviePreviewDTO>> getAllMovies() {
        List<MoviePreviewDTO> movies = movieService.getAllMoviesDTO();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieReadDTO> getMovieById(@PathVariable UUID id) {
        MovieReadDTO movie = movieService.getMovieByIdDTO(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MoviePreviewDTO> updateMovie(@PathVariable UUID id, @RequestBody MovieCreateUpdateDTO movieDTO) {
        Optional<Movie> foundMovieOptional = movieService.findById(id);
        Movie foundMovie = foundMovieOptional.get();
        String oldTitle = foundMovie.getName();
        MoviePreviewDTO movie=movieService.updateMovieDTO(id, movieDTO);
        if(movie!= null){
            String url = "http://localhost:8081/api/movies/" + oldTitle;
            restTemplate.put(url, movieDTO);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{movieName}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieName) {

        Movie movie=movieService.deleteMovieDTO(movieName);
        if (movie!= null){
            String url = "http://localhost:8081/api/movies/" + movieName;
            restTemplate.delete(url);
            return  ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build() ;
        }
    }


}
