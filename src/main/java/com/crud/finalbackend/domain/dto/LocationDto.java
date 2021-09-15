package com.crud.finalbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private String label;
    private String countryCode;
    private Double latitude;
    private Double longitude;
}
