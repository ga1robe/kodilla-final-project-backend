package com.crud.finalbackend.domain;

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
public class HikingTrail {
    private String departurePoint;
    private String destinationPoint;
    private BigInteger distance;
    private List<String> midPoints;
    private LocalDate hikingTripDate;
}
