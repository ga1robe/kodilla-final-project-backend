package com.crud.finalbackend.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRegisterDto {
    private String name;
    private String surname;
    private String email;
    private String securePassword;
}
