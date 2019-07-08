package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HappeningDTO {

    private UUID happeningId;
    private String name;
    private String description;

    private Long numOfParticipants;
    private Long maxNumOfParticipants;
    private Long minNumOfParticipants;

    private LocalDateTime when;

    private LocationDTO location;


    public HappeningDTO(HappeningDTO dto) {
        this.happeningId = dto.happeningId;
        this.name = dto.name;
        this.description = dto.description;
        this.numOfParticipants = dto.numOfParticipants;
        this.maxNumOfParticipants = dto.maxNumOfParticipants;
        this.minNumOfParticipants = dto.minNumOfParticipants;
        this.when = dto.when;
        this.location = dto.location;
    }

}
