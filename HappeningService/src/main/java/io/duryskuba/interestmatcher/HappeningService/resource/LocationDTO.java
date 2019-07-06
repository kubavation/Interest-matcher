package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocationDTO {

    private Long locationId;
    private String country;
    private String city;
    private String street;

    private double lat;
    private double lan;
}
