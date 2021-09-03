package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.ChargeStatus;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class HikingTrailDto {
    private LocationDto departurePoint;
    private LocationDto destinationPoint;
    private String departureName;
    private String destinationName;
    private ChargeStatus charge;
}
