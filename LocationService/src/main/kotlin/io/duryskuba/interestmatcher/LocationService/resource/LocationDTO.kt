package io.duryskuba.interestmatcher.LocationService.resource

import java.util.*

data class LocationDTO (

        var id: UUID? = null,
        var country: String? = null,
        var city: String? = null,
        var street: String? = null,
        var lat: Double? = null,
        var lan: Double? = null,
        var locationStr: String? = null //+ valid address?
) {


    constructor(dto: LocationDTO) : this() {
        this.id = dto.id
        this.country = dto.country
        this.street = dto.street
        this.city = dto.city
        this.lat = dto.lat
        this.lan = dto.lan
        this.locationStr = dto.locationStr
    }
}