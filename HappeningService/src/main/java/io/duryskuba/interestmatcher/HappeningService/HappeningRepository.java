package io.duryskuba.interestmatcher.HappeningService;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HappeningRepository extends MongoRepository<Happening, String> {
}
