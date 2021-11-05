package com.jumia.api.service;

import com.jumia.api.dto.CustomerDTO;
import com.jumia.api.entity.Customer;
import com.jumia.api.enumeration.Country;
import com.jumia.api.mapper.CustomerMapper;
import com.jumia.api.repository.CustomerRepository;
import com.jumia.api.repository.CustomerSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * This method is used to filter customers by country code AND | OR phone valid
     * @param countryCode The passed country code to filter with
     * @param phoneValid The passed phone valid flag to filter with
     * @return List of matched customers
     */
    public List<CustomerDTO> getCustomers(String countryCode, Boolean phoneValid) {
        List<CustomerDTO> result = new ArrayList<>();
        Specification<Customer> customerSpecification = CustomerSpecifications.getCustomerSpecifications(countryCode, phoneValid);
        List<Customer> customers = customerRepository.findAll(customerSpecification);
        customers.forEach(customer -> {
            final String phonePrefix = customer.getPhone().substring(0, customer.getPhone().indexOf(')') + 1);
            Optional.ofNullable(Country.fromPhonePrefix(phonePrefix)).map(country -> result.add(customerMapper.fromCustomer(customer, country)));
        });
        return result;
    }

}
