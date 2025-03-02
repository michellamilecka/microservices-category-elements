package org.example;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @NonNull
    Optional<Movie> findById(@NonNull UUID id);

    List<Movie> findAll();
    Optional<Movie> findByName(String name);
}
