package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrailOffer {
    private HikingTrailConnectionWithWeather thereHikingTrail;
    private HikingTrailConnectionWithWeather returnHikingTrail;
    private BigInteger totalDistance;

    @Override
    public String toString() {
        return "Trail{" +
                "HikingTrail there=" + thereHikingTrail +
                ", HikingTrail return=" + returnHikingTrail +
                ", with total Distance of=" + totalDistance +
                '}';
    }
}
