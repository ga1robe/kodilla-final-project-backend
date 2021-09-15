package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.ChargeStatus;
import lombok.*;

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
