package org.example;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name="characters")
public class Character implements Comparable<Character>, Serializable {
    @Id
    @Column(name="character_id")
    @Builder.Default
    private UUID characterID= UUID.randomUUID();
    @Column(name="name")
    private String name;
    @Column(name="name_of_actor")
    private String nameOfActor;
    @Column(name="last_name_of_actor")
    private String lastNameOfActor;
    @Column(name="estimated_age")
    private int estimatedAge;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Override
    public int compareTo(Character c){
        return this.name.compareTo(c.name);
    }
}
