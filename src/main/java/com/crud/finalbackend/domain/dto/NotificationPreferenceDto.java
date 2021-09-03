package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.User;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NotificationPreferenceDto {
    private Long id;
    private User user;
    private String trailBegin;
    private String trailEnd;
    private Integer distance;
    private BigInteger minTemperature;
}
