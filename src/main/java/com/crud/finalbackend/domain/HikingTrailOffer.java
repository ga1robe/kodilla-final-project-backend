package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HikingTrailOffer {
    private HikingTrailConnectionWithWeather hikingTrailConnectionWithWeather;
    private BigDecimal price;
}
