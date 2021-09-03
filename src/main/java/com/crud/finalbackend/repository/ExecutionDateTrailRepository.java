package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.ExecutionDateTrail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionDateTrailRepository extends CrudRepository<ExecutionDateTrail, Long> {

    @Override
    ExecutionDateTrail save(ExecutionDateTrail record);

    @Override
    List<ExecutionDateTrail> findAll();

    @Override
    void deleteAll();
}
