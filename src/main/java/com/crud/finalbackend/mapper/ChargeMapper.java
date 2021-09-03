package com.crud.finalbackend.mapper;

import com.crud.finalbackend.domain.Charge;
import com.crud.finalbackend.domain.dto.ChargeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChargeMapper {

    public Charge mapToCharge(final ChargeDto dto) {
        return Charge.builder()
                .id( dto.getId() )
                .chargeDate( (dto.getChargeDate().equals("UNPAID"))? null : LocalDate.parse(dto.getChargeDate()) )
                .status( dto.getStatus() )
                .value( dto.getValue() )
                .build();
    }

    public List<Charge> mapToPaymentList(final List<ChargeDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToCharge)
                .collect(Collectors.toList());
    }

    /**
     * NOTE FOR REVIEW:
     * Method checks if result is Null or not to not cause NullPointerException.
     * Way better solution would be Optional<LocalDate>, but it cannot be saved to database and would require
     * refactoring whole code to 3 layers (database entities, domain, dto) that would consume too much time.
     * Possible refactoring in the future with more time.
     *
     * @param charge
     * @return
     */
    private String getChargeDateString(final Charge charge) {
        if(charge.isChargeDate()) {
            return charge.getChargeDate().toString();
        }

        return "UNPAID";
    }

    public ChargeDto mapToDto(final Charge charge) {
        return ChargeDto.builder()
                .id( charge.getId() )
                .chargeDate( this.getChargeDateString(charge) )
                .status( charge.getStatus() )
                .value( charge.getValue() )
                .build();
    }

    public List<ChargeDto> mapToDtoList(final List<Charge> chargeList) {
        return chargeList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
