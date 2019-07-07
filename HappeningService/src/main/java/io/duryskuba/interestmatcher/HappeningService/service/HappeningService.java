package io.duryskuba.interestmatcher.HappeningService.service;

import io.duryskuba.interestmatcher.HappeningService.repository.HappeningRepository;
import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class HappeningService {

    private HappeningRepository happeningRepository;

    public HappeningService(HappeningRepository happeningRepository) {
        this.happeningRepository = happeningRepository;
    }

    public Collection<Happening> findAll() {
        return happeningRepository.findAll();
    }

    public Happening findById(UUID id) {
        return happeningRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }


    //  public List<HappeningService> findAll()

}

