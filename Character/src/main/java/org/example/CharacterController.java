package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final MovieService movieService;

    public CharacterController(CharacterService characterService, MovieService movieService) {
        this.characterService = characterService;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterPreviewDTO>> getAllCharacters() {
        List<CharacterPreviewDTO> characterList = characterService.getAllCharactersDTO();
        return ResponseEntity.ok(characterList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterReadDTO> getCharacterById(@PathVariable UUID id) {
        CharacterReadDTO character = characterService.getCharacterByIdDTO(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterPreviewDTO> updateCharacter(@PathVariable UUID id, @RequestBody CharacterCreateUpdateDTO characterDTO) {
        CharacterPreviewDTO character=characterService.updateCharacterDTO(id, characterDTO);
        if(character!= null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        Character character=characterService.deleteCharacterDTO(id);
        if (character!= null){
            return  ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build() ;
        }
    }

    @GetMapping("/{movieId}/characters")
    public ResponseEntity<List<CharacterPreviewDTO>> getCharactersByMovieId(@PathVariable UUID id) {
        List<CharacterPreviewDTO> characters = movieService.getCharactersByMovieId(id);
        if(characters==null){
            return ResponseEntity.notFound().build();
        }
        if (characters.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(characters);
    }
    @PostMapping("/{id}/characters")
    public ResponseEntity<CharacterPreviewDTO> addCharacterToMovie(@PathVariable UUID id, @RequestBody CharacterCreateUpdateDTO characterDTO) {

        CharacterPreviewDTO newCharacter=characterService.addCharacterToMovie(id,characterDTO);
        if(newCharacter==null){
            return ResponseEntity.notFound().build();
        }else{

            URI location=URI.create(String.format("/api/movies/%s/characters/%s", id, newCharacter.getCharacterID()));
            return ResponseEntity.created(location).body(newCharacter);
        }

    }



}
