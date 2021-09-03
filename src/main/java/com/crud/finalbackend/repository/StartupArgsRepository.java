package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.StartupArgs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartupArgsRepository extends CrudRepository<StartupArgs, Long> {

    @Override
    StartupArgs save(StartupArgs startupArgs);

    @Override
    List<StartupArgs> findAll();

    @Override
    void deleteAll();
}
