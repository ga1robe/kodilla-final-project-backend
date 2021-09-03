package com.crud.finalbackend.domain;

import com.crud.finalbackend.domain.dto.LocationDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="TRAIL_SEARCHES")
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HikingTrailRequest {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double departurePointLatitude;

    @NotNull
    private Double departurePointLongitude;

    @NotNull
    private Double destinationPointLatitude;

    @NotNull
    private Double destinationPointLongitude;

    @NotNull
    private String trailBegin;

    @NotNull
    private String trailEnd;

    @NotNull
    private String countryCode;

    @NotNull
    private Integer distance;

    @NotNull
    private LocalDate hikingDay;

    @Override
    public String toString() {
        return "TrailSearchRequest{" +
                ", From'" + trailBegin + '\'' +
                ", To='" + trailEnd + '\'' +
                ", Country='" + countryCode + '\'' +
                ", distance=" + distance + " km" +
                ", on=" + hikingDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HikingTrailRequest)) return false;
        HikingTrailRequest that = (HikingTrailRequest) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTrailBegin(), that.getTrailBegin()) && Objects.equals(getTrailEnd(), that.getTrailEnd()) && Objects.equals(getDistance(), that.getDistance()) && Objects.equals(getHikingDay(), that.getHikingDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrailBegin(), getTrailEnd(), getDistance(), getHikingDay());
    }
}
