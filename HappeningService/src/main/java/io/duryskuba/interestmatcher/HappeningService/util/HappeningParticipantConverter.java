package io.duryskuba.interestmatcher.HappeningService.util;

import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipant;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;

public class HappeningParticipantConverter {

    public static HappeningParticipant toEntity(HappeningParticipantDTO dto) {
        return HappeningParticipant.builder()
                    .happeningId(dto.getHappeningId())
                    .participantId(dto.getParticipantId())
                    .joinedAt(dto.getJoinedAt())
                    .userId(dto.getUserId())
                    .build();

    }

    public static HappeningParticipantDTO toDTO(HappeningParticipant participant) {
        return HappeningParticipantDTO.builder()
                    .userId(participant.getUserId())
                    .joinedAt(participant.getJoinedAt())
                    .participantId(participant.getParticipantId())
                    .happeningId(participant.getHappeningId())
                    .build();
    }

}
