package io.duryskuba.interestmatcher.LocationService.service

import io.duryskuba.interestmatcher.LocationService.repository.LocationRepository
import io.duryskuba.interestmatcher.LocationService.resource.Location
import io.duryskuba.interestmatcher.LocationService.resource.LocationDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class LocationService(val locationRepository: LocationRepository) {

    fun findById(id: UUID) = locationRepository.findById(id)

    fun createLocation(location: Location) = locationRepository.save(location)

    fun findCoordsOfPlace(location: LocationDTO) {

    }
}