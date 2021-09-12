package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.User;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PreferredDto {
    private Long id;
//    private UserDto userDto;
    private Long userId;
    private String trailBegin;
    private String trailEnd;
    private BigInteger distance;
    private Integer minTemperature;
}
