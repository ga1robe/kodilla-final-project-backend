package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.Trail;
import com.crud.finalbackend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailRepository extends CrudRepository<Trail, Long> {

    @Override
    Trail save(Trail trail);

    @Override
    List<Trail> findAll();

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);

    @Override
    Optional<Trail> findById(Long id);

    void deleteAllByUser(User user);

    List<Trail> findAllByTrailBegin(String trailBegin);

    List<Trail> findAllByTrailEnd(String trailEnd);

    List<Trail> findAllByUser(User user);
}
