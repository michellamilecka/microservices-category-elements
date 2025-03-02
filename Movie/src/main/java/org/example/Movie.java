package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="movies")
public class Movie implements Comparable<Movie>, Serializable {
    @Id
    @Column(name="movie_id")
    @Builder.Default
    private UUID movieID= UUID.randomUUID();
    @Column(name="name")
    private String name;
    @Column(name="year_of_premiere")
    private int yearOfPremiere;


    @Override
    public int compareTo(Movie m){
        return this.name.compareTo(m.name);
    }
}
