package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Happening {

    @Id
    private String id;
    private String name;
    private String description;

    private Long maxNumberOfParticipants;
    private Long minNumberOfParticipants;

    private LocalDateTime when;
    private String locationId;


    public Happening(String id, String name ) { this.id = id; this.name = name; } //todo remove
}
