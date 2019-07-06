package io.duryskuba.interestmatcher.LocationService.repository

import io.duryskuba.interestmatcher.LocationService.resource.Location
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LocationRepository: MongoRepository<Location, UUID>