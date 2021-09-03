package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.ExecutionDateTrail;
import com.crud.finalbackend.repository.ExecutionDateTrailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExecutionDateTrailService {
    private final ExecutionDateTrailRepository repository;

    public void addTrail(final ExecutionDateTrail timeTrail) {
        repository.save(timeTrail);
    }

    public List<ExecutionDateTrail> getAllTrails(){
        return repository.findAll();
    }

    public void deleteAllTrails() {
        repository.deleteAll();
    }
}
