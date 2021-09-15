package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HikingTrailConnectionWithWeather {
    private Preference preference;
    private BigDecimal expectedTemperature;

    public int compareTo(HikingTrailConnectionWithWeather other) {
        return this.getPreference().getDistance().compareTo(other.getPreference().getDistance());
    }

    @Override
    public String toString() {
        return "TrailOfferConnectionWithWeather{" +
                "hiking trail=" + preference +
                ", expectedTemperature=" + expectedTemperature +
                '}';
    }
}
