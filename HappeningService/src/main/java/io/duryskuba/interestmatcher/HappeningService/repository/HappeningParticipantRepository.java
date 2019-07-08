package io.duryskuba.interestmatcher.HappeningService.repository;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HappeningParticipantRepository extends MongoRepository<HappeningParticipant, String> {
    Long countAllByHappeningId(String happeningId);
    Optional<HappeningParticipant> findFirstByHappeningIdAndParticipantId(String happeningId,
                                                                          String participantId);
}
