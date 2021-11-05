package com.jumia.api.enumeration;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryTest {


    @Test
    void shouldReturnCountry_whenFromPhonePrefixIsCalled() {
        Country country = Country.fromPhonePrefix("(237)");
        assertEquals("CAMEROON", country.toString());
    }

    @Test
    void shouldThrowIllegalException_whenFromPhonePrefixIsCalledWithNotFoundPrefix() {
        assertThrows(IllegalArgumentException.class, () -> Country.fromPhonePrefix("not_found_phone_prefix"));
    }

    @Test
    void shouldThrowIllegalException_whenFromPhonePrefixIsCalledWithNullPrefix() {
        assertThrows(IllegalArgumentException.class, () -> Country.fromPhonePrefix(null));
    }

    @Test
    void shouldReturnCountry_whenGetCountriesByCodeIsCalled() {
        List<Country> countries = Country.getCountriesByCode("237");
        assertEquals(1, countries.size());
        assertEquals("CAMEROON", countries.get(0).toString());
    }

    @Test
    void shouldReturnAllCountries_whenGetCountriesByCodeIsCalledWithNullCode() {
        List<Country> countries = Country.getCountriesByCode(null);
        assertEquals(5, countries.size());
        assertEquals("CAMEROON", countries.get(0).toString());
        assertEquals("UGANDA", countries.get(4).toString());
    }

    @Test
    void shouldThrowIllegalException_whenGetCountriesByCodeIsCalledWithNotFoundCode() {
        assertThrows(IllegalArgumentException.class, () -> Country.getCountriesByCode("not_found_country_code"));
    }

}