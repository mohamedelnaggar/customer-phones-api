package com.jumia.api.controller;


import com.jumia.api.dto.CountryDTO;
import com.jumia.api.enumeration.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getCountries() {
        List<CountryDTO> countryDTOS = new ArrayList<>();
        Arrays.stream(Country.values()).forEach(country -> countryDTOS.add(
                CountryDTO.builder()
                        .countryCode(country.getCountryCode())
                        .countryName(country.getCountryName())
                        .build()
        ));
        return ResponseEntity.ok(countryDTOS);
    }

}
