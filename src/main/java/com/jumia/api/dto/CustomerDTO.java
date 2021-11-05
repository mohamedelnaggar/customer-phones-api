package com.jumia.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

    private Integer id;
    private String name;
    private String phone;
    private String countryCode;
    private String country;
    private Boolean phoneValid;

}
