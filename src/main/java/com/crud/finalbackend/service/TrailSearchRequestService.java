package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.HikingTrailRequest;
import com.crud.finalbackend.repository.TrailSearchRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TrailSearchRequestService {
    private final TrailSearchRequestRepository repository;

    @Transactional
    public void addSearchRequest(final HikingTrailRequest request) {
        repository.save(request);
    }

    public List<HikingTrailRequest> getAllRequests() {
        return repository.findAll();
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}
