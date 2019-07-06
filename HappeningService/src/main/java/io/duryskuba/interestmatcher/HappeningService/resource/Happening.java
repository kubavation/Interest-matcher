package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "happenings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Happening {

    @Id
    private UUID id;
    private String name;
    private String description;

    private Long maxNumberOfParticipants;
    private Long minNumberOfParticipants;

    private LocalDateTime when;
    private Long locationId;


    public Happening(UUID id, String name ) { this.id = id; this.name = name; } //todo remove
}
