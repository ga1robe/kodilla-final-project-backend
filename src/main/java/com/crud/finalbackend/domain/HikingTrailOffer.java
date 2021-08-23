package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HikingTrailOffer {
    private TrailOfferConnectionWithWeather trailOfferConnectionWithWeather;
    private BigDecimal price;
}
