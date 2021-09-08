package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.Preference;
import com.crud.finalbackend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Long> {

    @Override
    Preference save(Preference preference);

    @Override
    List<Preference> findAll();

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);

    @Override
    Optional<Preference> findById(Long id);

    void deleteAllByUser(User user);

    List<Preference> findAllByTrailBegin(String trailBegin);

    List<Preference> findAllByTrailEnd(String trailEnd);

    List<Preference> findAllByUser(User user);
}
