package com.jumia.api.enumeration;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Country {

    CAMEROON("Cameroon", "237", "(237)", "\\(237\\) ?[2368]\\d{7,8}$"),
    ETHIOPIA("Ethiopia", "251", "(251)", "\\(251\\) ?[1-59]\\d{8}$"),
    MOROCCO("Morocco", "212", "(212)", "\\(212\\) ?[5-9]\\d{8}$"),
    MOZAMBIQUE("Mozambique", "258", "(258)", "\\(258\\) ?[28]\\d{7,8}$"),
    UGANDA("Uganda", "256", "(256)", "\\(256\\) ?\\d{9}$");

    private String countryName;
    private String countryCode;
    private String phonePrefix;
    private String phonePattern;

    Country(String countryName, String countryCode, String phonePrefix, String phonePattern) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.phonePrefix = phonePrefix;
        this.phonePattern = phonePattern;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public String getPhonePattern() {
        return phonePattern;
    }

    /**
     * This method used to get country bt code or all countries if code is empty
     * @param countryCode The passed country code to filter with
     * @return List of matched countries to have one country or all countries
     */
    public static List<Country> getCountriesByCode(String countryCode) {
        List<Country> filteredCountries = new ArrayList<>();
        if(StringUtils.isEmpty(countryCode)) {
            filteredCountries = Arrays.stream(Country.values()).collect(Collectors.toList());
        } else {
            filteredCountries.add(Arrays.stream(Country.values())
                    .filter(c -> c.getCountryCode().equals(countryCode)).findFirst().orElseThrow(IllegalArgumentException::new));
        }
        return filteredCountries;
    }

    /**
     * This method is used to get Country by phone prefix
     * @param phonePrefix The passed phone prefix to filter with
     * @return The matched country
     */
    public static Country fromPhonePrefix(String phonePrefix) {
        return Optional.ofNullable(phonePrefix).map(prefix -> Arrays.stream(Country.values())
                .filter(c -> c.getPhonePrefix().equals(phonePrefix)).findFirst().orElseThrow(IllegalArgumentException::new)).orElseThrow(IllegalArgumentException::new);
    }
}
