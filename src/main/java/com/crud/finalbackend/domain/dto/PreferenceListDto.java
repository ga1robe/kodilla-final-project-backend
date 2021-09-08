package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.Preference;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NotNull
@Getter
public class PreferenceListDto {
    private List<PreferenceDto> preferences;
}
