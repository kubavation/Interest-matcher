package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "happening_participant")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HappeningParticipant {

    @Id
    private String participantId;
    private String happeningId;
    private Long userId;

    private LocalDateTime joinedAt;
}
