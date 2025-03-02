package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }

    @Override
    public Optional<Movie> findById(UUID id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie) {
        return this.movieRepository.save(movie);
    }

    @Override
    public void delete(UUID id) {
        this.movieRepository.deleteById(id);
    }
    @Override
    public MoviePreviewDTO createMovieDTO(MovieCreateUpdateDTO movieDTO) {
        Movie movie = Movie.builder()
                .name(movieDTO.getName())
                .yearOfPremiere(movieDTO.getYearOfPremiere())
                .build();

        Movie savedMovie = movieRepository.save(movie);

        return new MoviePreviewDTO(savedMovie.getMovieID(), savedMovie.getName());
    }

    @Override
    public List<MoviePreviewDTO> getAllMoviesDTO(){
        List<Movie> allMovies=movieRepository.findAll();
        UUID id;
        String nameMovie;
        List<MoviePreviewDTO> DTOMovies=new ArrayList<>();
        for (Movie movie : allMovies){
            id=movie.getMovieID();
            nameMovie=movie.getName();
            MoviePreviewDTO movie1= MoviePreviewDTO.builder()
                    .movieID(id)
                    .name(nameMovie)
                    .build();
            DTOMovies.add(movie1);
        }

        return DTOMovies;

    }
    @Override
    public MovieReadDTO getMovieByIdDTO(UUID ID){
        if(ID==null){
            throw new IllegalArgumentException("ID is null");
        }
        Optional<Movie> foundMovieOptional = movieRepository.findById(ID);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie=foundMovieOptional.get();
        int year=foundMovie.getYearOfPremiere();
        String name=foundMovie.getName();

        MovieReadDTO movieDTO= MovieReadDTO.builder()
                .movieID(ID)
                .name(name)
                .yearOfPremiere(year)
                .build();

        return movieDTO;
    }
    @Override
    public MoviePreviewDTO updateMovieDTO(UUID ID, MovieCreateUpdateDTO movie){
        if(ID==null){
            throw new IllegalArgumentException("ID is null");
        }
        Optional<Movie> foundMovieOptional = movieRepository.findById(ID);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie=foundMovieOptional.get();
        String dtoName=movie.getName();
        int dtoYear=movie.getYearOfPremiere();
        if(dtoName !=null){
            foundMovie.setName(dtoName);
        }
        if(dtoYear >0){
            foundMovie.setYearOfPremiere(dtoYear);
        }

        Movie updatedMovie = movieRepository.save(foundMovie);

        MoviePreviewDTO movieDTO= MoviePreviewDTO.builder()
                .movieID(updatedMovie.getMovieID())
                .name(updatedMovie.getName())
                .build();
        return movieDTO;

    }
    public Movie deleteMovieDTO(String movieName){
        Optional<Movie> foundMovieOptional = movieRepository.findByName(movieName);

        if (foundMovieOptional.isEmpty()) {
            return null;
        }

        Movie foundMovie = foundMovieOptional.get();

        movieRepository.delete(foundMovie);
        return foundMovie;
    }


}
