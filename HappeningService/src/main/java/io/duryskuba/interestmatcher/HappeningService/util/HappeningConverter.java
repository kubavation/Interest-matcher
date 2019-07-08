package io.duryskuba.interestmatcher.HappeningService.util;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;

public class HappeningConverter {

    public static Happening toEntity(HappeningDTO dto) {
        return new Happening();
    }

    public static HappeningDTO toDTO(Happening happening) {
        return HappeningDTO.builder().build();
    }

}
