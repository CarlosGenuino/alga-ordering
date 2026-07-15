package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.Objects;

public record Address(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        ZipCode zipCode) {

    public Address(String street, String number, String complement, String neighborhood, String city, String state, ZipCode zipCode) {
        Objects.requireNonNull(street);
        Objects.requireNonNull(number);
        Objects.requireNonNull(neighborhood);
        Objects.requireNonNull(city);
        Objects.requireNonNull(state);

        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
