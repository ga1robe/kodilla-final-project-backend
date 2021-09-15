package com.crud.finalbackend.domain.dto;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PreferenceDto {
    private Long id;
    private Long userId;
    private String trailBegin;
    private String trailEnd;
    private BigInteger distance;
    private Integer minTemperature;
}
