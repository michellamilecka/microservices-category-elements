package org.example;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MoviePreviewDTO {
    private UUID movieID;
    private String name;
}

