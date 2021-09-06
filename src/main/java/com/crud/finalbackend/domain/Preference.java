package com.crud.finalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name="NOTIFICATION_PREFERENCES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Preference {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @NotNull
    private String trailBegin;

    @NotNull
    private String trailEnd;

    @NotNull
    private BigInteger distance;

    @NotNull
    private Integer minTemperature;

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "id=" + id +
                ", user=" + user +
                ", trailBegin='" + trailBegin + '\'' +
                ", trailEnd='" + trailEnd + '\'' +
                ", distance=" + distance +
                ", minTemperature=" + minTemperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preference)) return false;
        Preference that = (Preference) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getTrailBegin(), that.getTrailBegin()) && Objects.equals(getTrailEnd(), that.getTrailEnd()) && Objects.equals(getDistance(), that.getDistance()) && Objects.equals(getMinTemperature(), that.getMinTemperature());
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getUser(), getTrailBegin(), getTrailEnd(), getDistance(), getMinTemperature());
//    }
}
