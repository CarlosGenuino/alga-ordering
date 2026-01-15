package com.algaworks.algashopping.ordering.domain.entity;

import com.algaworks.algashopping.ordering.domain.utility.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {
    @Test
    void testingCustomer(){
        int points = 10;
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(2000, 6, 30),
                "johndoe@nowmail.com",
                "478-256-2504",
                "366-08-0857",
                true,
                OffsetDateTime.now()
                );
        customer.addLoyaltyPoints(points);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals(points, customer.loyaltyPoints());
    }
}