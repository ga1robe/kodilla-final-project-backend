package com.crud.finalbackend.repository;

import com.crud.finalbackend.domain.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChargeRepository extends CrudRepository<Charge, Long> {

    @Override
    Charge save(Charge charge);

    @Override
    Optional<Charge> findById(Long id);

    @Override
    List<Charge> findAll();

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);

    List<Charge> findAllByChargeDate(LocalDate date);
}
