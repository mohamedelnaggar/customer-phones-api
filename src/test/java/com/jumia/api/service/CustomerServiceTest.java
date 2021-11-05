package com.jumia.api.service;

import com.jumia.api.dto.CustomerDTO;
import com.jumia.api.entity.Customer;
import com.jumia.api.mapper.CustomerMapper;
import com.jumia.api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository mockedCustomerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setup() {
        CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class );
        this.customerService = new CustomerService(mockedCustomerRepository, customerMapper);
    }

    @Test
    void shouldReturnCustomerDTOs_whenGetCustomerIsCalled() {
        Customer mockedSingleCustomer = Customer.builder()
                .id(1)
                .name("MICHAEL MICHAEL")
                .phone("(237) 677046616")
                .build();
        List<Customer> mockedCustomers = Collections.singletonList(mockedSingleCustomer);
        when(mockedCustomerRepository.findAll(any(Specification.class))).thenReturn(mockedCustomers);
        List<CustomerDTO> customers = customerService.getCustomers(null, null);
        assertEquals(1, customers.size());
        assertEquals("MICHAEL MICHAEL", customers.get(0).getName());
        assertEquals("Cameroon", customers.get(0).getCountry());
        assertTrue(customers.get(0).getPhoneValid());
    }

    @Test
    void shouldThrowIllegalException_whenGetCustomerIsCalledWithNotSupportedCountry() {
        assertThrows(IllegalArgumentException.class, () -> {
            Customer mockedSingleCustomer = Customer.builder().id(1).name("Mohamed").phone("(002) 01004740712").build();
            List<Customer> mockedCustomers = Collections.singletonList(mockedSingleCustomer);
            when(mockedCustomerRepository.findAll(any(Specification.class))).thenReturn(mockedCustomers);
            customerService.getCustomers(null, null);
        });
    }
}