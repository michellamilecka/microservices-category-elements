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
    private final CharacterRepository characterRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
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
    public void save(Movie movie) {
        this.movieRepository.save(movie);
    }

    @Override
    public void delete(UUID id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public Movie deleteMovieDTO(String movieName){
        Optional<Movie> foundMovieOptional = movieRepository.findByName(movieName);

        if (foundMovieOptional.isEmpty()) {
            return null;
        }

        Movie foundMovie = foundMovieOptional.get();

        movieRepository.delete(foundMovie);
        return foundMovie;
    }
    @Override
    public List<CharacterPreviewDTO> getCharactersByMovieTitle(String title) {
        Optional<Movie> foundMovieOptional = movieRepository.findByName(title);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie = foundMovieOptional.get();
        List<Character> allCharacters = foundMovie.getCharactersInMovie();
        List<CharacterPreviewDTO> charactersDTO = new ArrayList<>();
        UUID characterID;
        String characterName;
        for (Character character : allCharacters) {
            characterID = character.getCharacterID();
            characterName = character.getName();
            CharacterPreviewDTO character1 = CharacterPreviewDTO.builder()
                    .characterID(characterID)
                    .name(characterName)
                    .build();
            charactersDTO.add(character1);
        }
        return charactersDTO;
    }

    @Override
    public List<CharacterPreviewDTO> getCharactersByMovieId(UUID id){

        Optional<Movie> foundMovieOptional = movieRepository.findById(id);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie=foundMovieOptional.get();
        List<Character> allCharacters=foundMovie.getCharactersInMovie();
        List<CharacterPreviewDTO> charactersDTO=new ArrayList<>();
        UUID characterID;
        String characterName;
        for (Character character : allCharacters){
            characterID=character.getCharacterID();
            characterName=character.getName();
            CharacterPreviewDTO character1= CharacterPreviewDTO.builder()
                    .characterID(characterID)
                    .name(characterName)
                    .build();
            charactersDTO.add(character1);
        }
        return charactersDTO;
    }
    @Override
    public CharacterPreviewDTO addCharacterToMovie(UUID id, CharacterCreateUpdateDTO character) {

        Optional<Movie> foundMovieOptional = movieRepository.findById(id);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie=foundMovieOptional.get();

        Character newCharacter = Character.builder()
                .name(character.getName())
                .nameOfActor(character.getNameOfActor())
                .lastNameOfActor(character.getLastNameOfActor())
                .estimatedAge(character.getEstimatedAge())
                .movie(foundMovie)
                .build();


        Character saved = characterRepository.save(newCharacter);
        CharacterPreviewDTO previewDTO=CharacterPreviewDTO.builder()
                .characterID(saved.getCharacterID())
                .name(saved.getName())
                .build();

        return previewDTO;
    }
    @Override
    public CharacterPreviewDTO addCharacterToMovieByTitle(String title, CharacterCreateUpdateDTO character) {

        Optional<Movie> foundMovieOptional = movieRepository.findByName(title);
        if (foundMovieOptional.isEmpty()) {
            return null;
        }
        Movie foundMovie = foundMovieOptional.get();


        Character newCharacter = Character.builder()
                .name(character.getName())
                .nameOfActor(character.getNameOfActor())
                .lastNameOfActor(character.getLastNameOfActor())
                .estimatedAge(character.getEstimatedAge())
                .movie(foundMovie)
                .build();


        Character saved = characterRepository.save(newCharacter);


        CharacterPreviewDTO previewDTO = CharacterPreviewDTO.builder()
                .characterID(saved.getCharacterID())
                .name(saved.getName())
                .build();

        return previewDTO;
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
    @Override
    public MoviePreviewDTO updateMovieByName(String movieName, MovieCreateUpdateDTO movie) {
        if (movieName == null || movieName.isEmpty()) {
            throw new IllegalArgumentException("Movie name is null or empty");
        }

        // Znajdź film po nazwie (tytule)
        Optional<Movie> foundMovieOptional = movieRepository.findByName(movieName);
        if (foundMovieOptional.isEmpty()) {
            return null;  // Film nie został znaleziony
        }

        Movie foundMovie = foundMovieOptional.get();
        String dtoName = movie.getName();
        int dtoYear = movie.getYearOfPremiere();

        // Zaktualizuj dane filmu, jeśli nowe wartości są niepuste lub większe od 0
        if (dtoName != null && !dtoName.isEmpty()) {
            foundMovie.setName(dtoName);
        }
        if (dtoYear > 0) {
            foundMovie.setYearOfPremiere(dtoYear);
        }

        // Zapisz zaktualizowany film
        Movie updatedMovie = movieRepository.save(foundMovie);

        // Utwórz i zwróć DTO z zaktualizowanymi danymi
        MoviePreviewDTO movieDTO = MoviePreviewDTO.builder()
                .movieID(updatedMovie.getMovieID())
                .name(updatedMovie.getName())
                .build();

        return movieDTO;
    }


    @Override
    public void createMovie(UUID idMovie, MovieCreateUpdateDTO movieDTO) {
        Movie newMovie = Movie.builder()
                .movieID(idMovie)
                .name(movieDTO.getName())
                .yearOfPremiere(movieDTO.getYearOfPremiere())
                .build();


        movieRepository.save(newMovie);
    }

}
