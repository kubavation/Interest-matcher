package io.duryskuba.interestmatcher.HappeningService.util;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HappeningConverter {


    public static Happening toEntity(HappeningDTO dto) {
        return
                Happening.builder()
                    .id(UUID.randomUUID()) //????
                    .description(dto.getDescription())
                    .locationId(dto.getLocation() != null ? dto.getLocation().getLocationId() : null)
                    .maxNumberOfParticipants(dto.getMaxNumOfParticipants())
                    .minNumberOfParticipants(dto.getMinNumOfParticipants())
                    .name(dto.getName())
                    .when(dto.getWhen())
                    .locationId(dto.getLocationId())
                    .build();
    }

    public static HappeningDTO toDTO(Happening happening) {
        return HappeningDTO.builder().build();
    }

}
