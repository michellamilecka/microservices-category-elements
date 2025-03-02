package org.example;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CharacterPreviewDTO {
    private UUID characterID;
    private String name;
}
