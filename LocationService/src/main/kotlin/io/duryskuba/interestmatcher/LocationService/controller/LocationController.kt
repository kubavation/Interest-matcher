package io.duryskuba.interestmatcher.LocationService.controller

import io.duryskuba.interestmatcher.LocationService.resource.LocationDTO
import io.duryskuba.interestmatcher.LocationService.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class LocationController(val locationService: LocationService) {

    @GetMapping("/locations/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<LocationDTO>
        = ResponseEntity(locationService.findById(id),HttpStatus.OK)

    @PostMapping("/locations")
    fun create(locationDTO: LocationDTO): ResponseEntity<LocationDTO>
        = ResponseEntity(locationService.createLocation(locationDTO),HttpStatus.OK)
}