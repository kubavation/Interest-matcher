package io.duryskuba.interestmatcher.LocationService.resource

import java.util.*

data class LocationDTO (

        var id: UUID? = null,
        var country: String? = null,
        var city: String? = null,
        var street: String? = null,
        var lat: Double? = null,
        var lan: Double? = null
)