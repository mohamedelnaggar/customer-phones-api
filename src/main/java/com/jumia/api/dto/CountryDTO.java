package com.jumia.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDTO {
    private String countryCode;
    private String countryName;
}
