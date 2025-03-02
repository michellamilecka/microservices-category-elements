package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Character> findById(UUID id) {
        return this.characterRepository.findById(id);
    }

    @Override
    public List<Character> findByMovie(Movie movie) {
        return this.characterRepository.findByMovie(movie);
    }
    @Override
    public List<Character> findAll() {
        return this.characterRepository.findAll();
    }

    @Override
    public void save(Character character) {
        this.characterRepository.save(character);
    }

    @Override
    public void delete(UUID id) {
        characterRepository.deleteById(id);

    }

    @Override
    public List<CharacterPreviewDTO> getAllCharactersDTO(){
        List<Character> allCharacters=characterRepository.findAll();
        UUID id;
        String nameCharacter;
        List<CharacterPreviewDTO> DTOCharacters=new ArrayList<>();
        for (Character character : allCharacters){
            id=character.getCharacterID();
            nameCharacter=character.getName();
            CharacterPreviewDTO character1= CharacterPreviewDTO.builder()
                    .characterID(id)
                    .name(nameCharacter)
                    .build();
            DTOCharacters.add(character1);
        }

        return DTOCharacters;
    }

    @Override
    public CharacterReadDTO getCharacterByIdDTO(UUID ID){
        if(ID==null){
            throw new IllegalArgumentException("ID is null");
        }
        Optional<Character> foundCharacterOptional = characterRepository.findById(ID);
        if (foundCharacterOptional.isEmpty()) {
            return null;
        }
        Character foundCharacter=foundCharacterOptional.get();
        String nameCharacter=foundCharacter.getName();
        String nameActor=foundCharacter.getNameOfActor();
        String lastNameActor=foundCharacter.getLastNameOfActor();
        int age=foundCharacter.getEstimatedAge();
        String title=foundCharacter.getMovie().getName();

        CharacterReadDTO characterDTO= CharacterReadDTO.builder()
                .characterID(ID)
                .name(nameCharacter)
                .nameOfActor(nameActor)
                .lastNameOfActor(lastNameActor)
                .estimatedAge(age)
                .movieTitle(title)
                .build();

        return characterDTO;
    }

    @Override
    public CharacterPreviewDTO updateCharacterDTO(UUID ID, CharacterCreateUpdateDTO character){
        if(ID==null){
            throw new IllegalArgumentException("ID is null");
        }
        Optional<Character> foundCharacterOptional = characterRepository.findById(ID);
        if (foundCharacterOptional.isEmpty()) {
            return null;
        }
        Character foundCharacter=foundCharacterOptional.get();
        String dtoName=character.getName();
        String  dtonameActor=character.getNameOfActor();
        String dtoLastNameActor=character.getLastNameOfActor();
        int dtoAge=character.getEstimatedAge();
        if(dtoName !=null){
            foundCharacter.setName(dtoName);
        }
        if(dtonameActor !=null){
            foundCharacter.setNameOfActor(dtonameActor);
        }
        if(dtoLastNameActor !=null){
            foundCharacter.setLastNameOfActor(dtoLastNameActor);
        }
        if(dtoAge >0){
            foundCharacter.setEstimatedAge(dtoAge);
        }
        Character updatedCharacter = characterRepository.save(foundCharacter);

        CharacterPreviewDTO characterDTO= CharacterPreviewDTO.builder()
                .characterID(updatedCharacter.getCharacterID())
                .name(updatedCharacter.getName())
                .build();
        return characterDTO;

    }
    public Character deleteCharacterDTO(UUID id){
        Optional<Character> foundCharacterOptional = characterRepository.findById(id);
        if (foundCharacterOptional.isEmpty()) {
            return null;
        }
        Character foundCharacter=foundCharacterOptional.get();

        characterRepository.delete(foundCharacter);
        return foundCharacter;
    }

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
}
