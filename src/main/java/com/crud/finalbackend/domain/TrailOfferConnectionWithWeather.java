package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrailOfferConnectionWithWeather {
    private Trail trail;
    private BigDecimal expectedTemperature;

    @Override
    public String toString() {
        return "TrailOfferConnectionWithWeather{" +
                "hiking trail=" + trail +
                ", expectedTemperature=" + expectedTemperature +
                '}';
    }
}
