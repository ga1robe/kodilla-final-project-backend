package com.crud.finalbackend.controler;

import com.crud.finalbackend.domain.dto.ChargeListDto;
import com.crud.finalbackend.domain.Charge;
import com.crud.finalbackend.domain.dto.ChargeDto;
import com.crud.finalbackend.except.WrongDateFormatException;
import com.crud.finalbackend.mapper.ChargeMapper;
import com.crud.finalbackend.service.ChargeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.regex.Pattern;

@RestController
@RequestMapping
@AllArgsConstructor
public class ChargeController {
    private final ChargeService chargeService;
    private final ChargeMapper chargeMapper;

    @PostMapping("payments")
    public void addPayment(@RequestBody ChargeDto dto) {
        chargeService.addPayment( chargeMapper.mapToCharge(dto) );
    }

    @GetMapping("payments/{id}")
    public ChargeDto getPayment(@PathVariable("id") Long id) {
        return chargeMapper.mapToDto( chargeService.getPaymentById(id) );
    }

    @GetMapping("charges")
    public ChargeListDto getAllPayments() {
        return new ChargeListDto( chargeMapper.mapToDtoList( chargeService.getAllPayments()) );
    }

    @PutMapping("charges")
    @Transactional
    public ChargeDto updatePayment(@RequestBody ChargeDto updatingDto) {
        Charge charge = chargeService.getPaymentById( updatingDto.getId() );
        BigDecimal currentValue = charge.getValue().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal updatingValue = updatingDto.getValue().setScale(2, RoundingMode.HALF_EVEN);

        if(! charge.isChargeDate() && updatingDto.hasValidDate() ) { charge.setChargeDate( LocalDate.parse(updatingDto.getChargeDate()) ); }
        if(
                charge.isChargeDate()   &&
                        updatingDto.hasValidDate() &&
                        !( charge.getChargeDate().toString().equals( updatingDto.getChargeDate() )  )

        ) {charge.setChargeDate( LocalDate.parse(updatingDto.getChargeDate()) );}
        if(! charge.getStatus().equals( updatingDto.getStatus() ) ) { charge.setStatus( updatingDto.getStatus() ); }
        if(! currentValue.equals(updatingValue)) { charge.setValue( updatingDto.getValue() ); }

        return chargeMapper.mapToDto(charge);
    }

    @GetMapping("charges/")
    public  ChargeListDto getChargesByDate(@RequestParam("date") String date) {
        Pattern datePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        if(datePattern.matcher( date ).matches()) {
            return new ChargeListDto( chargeMapper.mapToDtoList( chargeService.getPaymentsByDate(LocalDate.parse(date)) ) );
        }
        throw new WrongDateFormatException();
    }
}
