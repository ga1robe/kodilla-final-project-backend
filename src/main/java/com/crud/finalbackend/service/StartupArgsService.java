package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.StartupArgs;
import com.crud.finalbackend.repository.StartupArgsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StartupArgsService {
    private final StartupArgsRepository repository;

    @Transactional
    public StartupArgs addStartupArgs(final StartupArgs args) {
        return repository.save(args);
    }

    public List<StartupArgs> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}
