package org.example;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<Character, UUID>
{
    @NonNull
    Optional<Character> findById(@NonNull UUID id);
    List<Character> findByMovie(Movie movie);

    List<Character> findAll();
}
