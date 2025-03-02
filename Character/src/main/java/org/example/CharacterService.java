package org.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CharacterService {
    Optional<Character> findById(UUID id);
    List<Character> findByMovie(Movie movie);

    List<Character> findAll();

    void save(Character character);

    void delete(UUID id);

    List<CharacterPreviewDTO> getAllCharactersDTO();

    CharacterReadDTO getCharacterByIdDTO(UUID ID);

    CharacterPreviewDTO updateCharacterDTO(UUID ID, CharacterCreateUpdateDTO character);

    Character deleteCharacterDTO(UUID id);
    CharacterPreviewDTO addCharacterToMovie(UUID id, CharacterCreateUpdateDTO character);

}
