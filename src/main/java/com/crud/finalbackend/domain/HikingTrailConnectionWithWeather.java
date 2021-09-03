package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HikingTrailConnectionWithWeather {
    private HikingTrail trail;
    private BigDecimal expectedTemperature;

//    @Override
    public int compareTo(HikingTrailConnectionWithWeather other) {
        return this.getTrail().getDistance().compareTo(other.getTrail().getDistance());
    }

    @Override
    public String toString() {
        return "TrailOfferConnectionWithWeather{" +
                "hiking trail=" + trail +
                ", expectedTemperature=" + expectedTemperature +
                '}';
    }
}
