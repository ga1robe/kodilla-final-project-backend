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
    private String begin;
    private String end;
    private Integer distance;
    private BigInteger totalApproaches;
    private BigInteger totalDepartures;
    private Integer transmissionTime;
    private String trailColor;

    @Override
    public String toString() {
        return "Hiking Trail{" +
                "BEGINNING='" + begin + '\'' +
                ", END='" + end + '\'' +
                ", DISTANCE=" + distance + " km" +
                ", TOTAL APPROACHES=" + totalApproaches + " m" +
                ", TOTAL DEPARTURES=" + totalDepartures + " m" +
                ", TRANSMISSION TIME=" + transmissionTime + " hours" +
                ", COLOR OF TRAIL=" + trailColor +
                '}';
    }
}
