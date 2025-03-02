package org.example;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MovieReadDTO {
    private UUID movieID;
    private String name;
    private int yearOfPremiere;

}


