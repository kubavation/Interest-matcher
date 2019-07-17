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
                    .id(UUID.randomUUID().toString())
                    .description(dto.getDescription())
                    .locationId(dto.getLocation() != null ? dto.getLocation().getLocationId() : null)
                    .maxNumberOfParticipants(dto.getMaxNumOfParticipants())
                    .minNumberOfParticipants(dto.getMinNumOfParticipants())
                    .name(dto.getName())
                    .when(dto.getWhen())
                    .locationStr(dto.getLocation() != null ? dto.getLocation().getLocationStr() : null )
                    .build();
    }

    public static HappeningDTO toDTO(Happening happening) {

        return
                HappeningDTO.builder()
                    .happeningId(happening.getId())
                    .description(happening.getDescription())
                    .locationId(happening.getLocationId())
                    .locationStr(happening.getLocationStr())
                    .maxNumOfParticipants(happening.getMaxNumberOfParticipants())
                    .minNumOfParticipants(happening.getMinNumberOfParticipants())
                    //.numOfParticipants(happening.get)
                    //todo number of participants
                .build();
    }

}
