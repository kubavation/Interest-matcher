package io.duryskuba.interestmatcher.LocationService.controller

import io.duryskuba.interestmatcher.LocationService.resource.LocationDTO
import io.duryskuba.interestmatcher.LocationService.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class LocationController(val locationService: LocationService) {

    @GetMapping("/locations/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<LocationDTO>
        = ResponseEntity(locationService.findById(id),HttpStatus.OK)

    @PostMapping("/locations")
    fun create(@RequestBody locationDTO: LocationDTO): ResponseEntity<LocationDTO>
        = ResponseEntity(locationService.createLocation(locationDTO),HttpStatus.OK)
}