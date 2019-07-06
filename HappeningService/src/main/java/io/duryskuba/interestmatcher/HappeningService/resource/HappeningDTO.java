package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HappeningDTO {

    private String happeningId;
    private String name;
    private String description;

    private Long numOfParticipants;
    private Long maxNumOfParticipants;
    private Long minNumOfParticipants;

    private LocalDateTime when;

    private LocationDTO location;


}
