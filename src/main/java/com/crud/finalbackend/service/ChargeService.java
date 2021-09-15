package com.crud.finalbackend.service;

import com.crud.finalbackend.domain.Charge;
import com.crud.finalbackend.domain.ServiceUsageRecord;
import com.crud.finalbackend.except.ChargeNotFoundException;
import com.crud.finalbackend.repository.ChargeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChargeService {
    private final ChargeRepository chargeRepository;

    public Charge getChargeById(final Long id) {
        return chargeRepository.findById(id).orElseThrow(ChargeNotFoundException::new);
    }

    public List<Charge> getAllCharges(){
        return chargeRepository.findAll();
    }

    public List<Charge> getChargesByDate(final LocalDate date) {
        return chargeRepository.findAllByChargeDate(date);
    }

    @Transactional
    public void deleteAllCharges() {
        chargeRepository.deleteAll();
    }

    @Transactional
    public void deleteChargeById(final Long id) {
        chargeRepository.deleteById(id);
    }
}