package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocationDTO {

    private UUID locationId;
    private String country;
    private String city;
    private String street;

    private double lat;
    private double lan;
}
