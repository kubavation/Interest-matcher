package io.duryskuba.interestmatcher.LocationService.resource

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "locations")
data class Location(

        @Id
        var id: String? = null,
        var country: String? = null,
        var city: String? = null,
        var street: String? = null,
        var lat: Double? = null,
        var lan: Double? = null
)
