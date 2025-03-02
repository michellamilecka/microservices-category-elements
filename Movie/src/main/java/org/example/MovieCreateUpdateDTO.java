package org.example;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MovieCreateUpdateDTO {
    private String name;
    private int yearOfPremiere;
}
