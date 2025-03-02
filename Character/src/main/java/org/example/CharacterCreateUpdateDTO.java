package org.example;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CharacterCreateUpdateDTO {
    private String name;
    private String nameOfActor;
    private String lastNameOfActor;
    private int estimatedAge;
}
