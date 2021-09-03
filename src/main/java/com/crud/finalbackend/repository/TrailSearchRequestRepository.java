package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.HikingTrailRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailSearchRequestRepository extends CrudRepository<HikingTrailRequest, Long> {
    @Override
    HikingTrailRequest save(HikingTrailRequest request);

    @Override
    List<HikingTrailRequest> findAll();

    @Override
    void deleteAll();
}
