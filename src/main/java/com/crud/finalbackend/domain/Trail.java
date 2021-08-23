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
    private Integer lowDistance;
    private Integer highDistance;
    private BigInteger totalApproaches;
    private BigInteger totalDepartures;
    private Integer lowTransmissionTime;
    private Integer highTransmissionTime;
    private String trailColor;

    @Override
    public String toString() {
        return "Hiking Trail{" +
                "BEGINNING='" + begin + '\'' +
                ", END='" + end + '\'' +
                ", DISTANCE=" + lowDistance + " km / " + highDistance + " km " +
                ", TOTAL APPROACHES=" + totalApproaches + " m" +
                ", TOTAL DEPARTURES=" + totalDepartures + " m" +
                ", TRANSMISSION TIME=" + lowTransmissionTime + "-" + highTransmissionTime + " hours" +
                ", COLOR OF TRAIL=" + trailColor +
                '}';
    }
}
