package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record FullName(
        String firstName,
        String lastName
) {
    public FullName(String firstName, String lastName) {
        Objects.requireNonNull(firstName, ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL);
        Objects.requireNonNull(lastName, ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL);

        if (firstName.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK);
        }

        if (lastName.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK);
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
