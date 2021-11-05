package com.jumia.api.mapper;

import com.jumia.api.dto.CustomerDTO;
import com.jumia.api.entity.Customer;
import com.jumia.api.enumeration.Country;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerMapperTest {

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void shouldReturnCustomerDTOWithValidPhone_whenFromCustomerIsCalledWithValidPhone() {
        Customer customer = Customer.builder()
                .id(1)
                .name("MICHAEL MICHAEL")
                .phone("(237) 677046616")
                .build();

        CustomerDTO customerDTO = customerMapper.fromCustomer(customer, Country.CAMEROON);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getPhone(), customerDTO.getPhone());
        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals("237", customerDTO.getCountryCode());
        assertEquals("Cameroon", customerDTO.getCountry());
        assertEquals(true, customerDTO.getPhoneValid());
    }

    @Test
    void shouldReturnCustomerDTOWithNotValidPhone_whenFromCustomerIsCalledWithNotValid() {
        Customer customer = Customer.builder()
                .id(1)
                .name("MICHAEL MICHAEL")
                .phone("(237) 67704")
                .build();

        CustomerDTO customerDTO = customerMapper.fromCustomer(customer, Country.CAMEROON);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getPhone(), customerDTO.getPhone());
        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals("237", customerDTO.getCountryCode());
        assertEquals("Cameroon", customerDTO.getCountry());
        assertEquals(false, customerDTO.getPhoneValid());
    }
}