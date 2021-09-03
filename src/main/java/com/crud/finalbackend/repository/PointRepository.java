package com.crud.finalbackend.repository;

import com.crud.finalbackend.external.api.points.domain.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    @Override
    Point save(Point point);

    @Override
    List<Point> findAll();

    @Override
    Optional<Point> findById(Long id);

    @Override
    void deleteAll();

    Optional<Point> findByName(String name);

    Boolean existsByName(String name);
}
