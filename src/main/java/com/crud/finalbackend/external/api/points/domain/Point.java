package com.crud.finalbackend.external.api.points.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="COUNTRIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
    @Id
    @NotNull
    @GeneratedValue
    @Column
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String trail;

    @NotNull
    @Column(unique = true)
    private Integer location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Objects.equals(name, point.getName()) && Objects.equals(trail, point.getTrail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, trail);
    }
}
