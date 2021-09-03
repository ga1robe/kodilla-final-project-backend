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
    private final ServiceUsageRecordService recordService;

    @Transactional
    public Charge addPayment(final Charge charge) {
        ServiceUsageRecord record = ServiceUsageRecord.builder()
                .whenExecuted(LocalDateTime.now())
                .serviceClass(this.getClass().getName())
                .methodArgument("payment")
                .build();
        recordService.addRecord(record);

        return chargeRepository.save(charge);
    }

    public Charge getPaymentById(final Long id) {
        return chargeRepository.findById(id).orElseThrow(ChargeNotFoundException::new);
    }

    public List<Charge> getAllPayments(){
        return chargeRepository.findAll();
    }

    public List<Charge> getPaymentsByDate(final LocalDate date) {
        return chargeRepository.findAllByChargeDate(date);
    }

    @Transactional
    public void deleteAllPayments() {
        chargeRepository.deleteAll();
    }

    @Transactional
    public void deletePaymentById(final Long id) {
        chargeRepository.deleteById(id);
    }
}
