package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "happening_participant")
@NoArgsConstructor
@AllArgsConstructor
public class HappeningParticipant {

    @Id
    private String id;
    private String happeningId;
    private Long userId;

    private LocalDateTime joinedAt;
}
