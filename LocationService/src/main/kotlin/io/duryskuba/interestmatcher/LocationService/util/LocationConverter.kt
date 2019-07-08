package io.duryskuba.interestmatcher.LocationService.util

import io.duryskuba.interestmatcher.LocationService.resource.Location
import io.duryskuba.interestmatcher.LocationService.resource.LocationDTO
import org.springframework.stereotype.Component

@Component
class LocationConverter {

    fun toEntity(locationDTO: LocationDTO): Location {
        var location: Location = Location()
        location.id = locationDTO.locationId
        location.city = locationDTO.city
        location.country = locationDTO.country
        location.street = locationDTO.street
        location.lat = locationDTO.lat
        location.lan = locationDTO.lan

        return location
    }

    fun toDto(location: Location): LocationDTO {
        var dto: LocationDTO = LocationDTO()
        dto.locationId = location.id
        dto.city = location.city
        dto.country = location.country
        dto.street = location.street
        dto.lan = location.lan
        dto.lat = location.lat
        dto.locationStr = "${dto.country}, ${dto.city}, ${dto.street}"

        return dto
    }
}