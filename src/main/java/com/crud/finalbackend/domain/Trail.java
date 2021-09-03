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
public class Trail {
    private String name;
    private String begin;
    private String end;
    private Integer distance;
    private BigInteger totalApproaches;
    private BigInteger totalDepartures;
    private Integer transmissionTime;
    private List<String> points;
    private String trailColor;

    @Override
    public String toString() {
        return "Hiking Trail{" +
                "NAME='" + name + '\'' +
                "BEGINNING='" + begin + '\'' +
                ", END='" + end + '\'' +
                ", DISTANCE=" + distance + " km" +
                ", TOTAL APPROACHES=" + totalApproaches + " m" +
                ", TOTAL DEPARTURES=" + totalDepartures + " m" +
                ", TRANSMISSION TIME=" + transmissionTime + " hours" +
                "POINTS ON TRAIL=" + String.join(",", points) +
                ", COLOR OF TRAIL=" + trailColor +
                '}';
    }
}
