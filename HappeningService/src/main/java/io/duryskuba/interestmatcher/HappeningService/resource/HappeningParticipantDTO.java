package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HappeningParticipantDTO {

    private String participantId;
    private Long userId;
    private String happeningId;
    private LocalDateTime joinedAt;

}
