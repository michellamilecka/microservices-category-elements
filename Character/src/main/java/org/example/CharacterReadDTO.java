package org.example;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CharacterReadDTO {
    private UUID characterID;
    private String name;
    private String nameOfActor;
    private String lastNameOfActor;
    private int estimatedAge;
    private String movieTitle;
}