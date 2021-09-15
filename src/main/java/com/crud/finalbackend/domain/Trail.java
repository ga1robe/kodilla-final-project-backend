package com.crud.finalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name="TRAILS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Trail {
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
    private String trailType;

    @NotNull
    private Boolean chargeStatus;

    @NotNull
    private BigInteger distance;

    @NotNull
    private Integer minTemperature;
}
