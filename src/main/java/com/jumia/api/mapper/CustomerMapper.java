package com.jumia.api.mapper;

import com.jumia.api.dto.CustomerDTO;
import com.jumia.api.entity.Customer;
import com.jumia.api.enumeration.Country;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "countryCode", expression = "java(country.getCountryCode())")
    @Mapping(target = "country", expression = "java(country.getCountryName())")
    @Mapping(target = "phoneValid", expression = "java(java.util.regex.Pattern.matches(country.getPhonePattern(), customer.getPhone()) ? true : false)")
    CustomerDTO fromCustomer(Customer customer, @Context Country country);

}
