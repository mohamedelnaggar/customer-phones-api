package com.jumia.api.controller;


import com.jumia.api.dto.CustomerDTO;
import com.jumia.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerController {

   private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getCustomers(@RequestParam(value = "countryCode", required = false) String countryCode,
                                                          @RequestParam(value = "phoneValid", required = false) Boolean phoneValid) {
        return ResponseEntity.ok(customerService.getCustomers(countryCode, phoneValid));
    }

}
