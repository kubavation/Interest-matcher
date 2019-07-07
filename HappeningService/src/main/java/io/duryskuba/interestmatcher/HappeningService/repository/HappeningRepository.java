package io.duryskuba.interestmatcher.HappeningService.repository;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HappeningRepository extends MongoRepository<Happening, UUID> {
}
