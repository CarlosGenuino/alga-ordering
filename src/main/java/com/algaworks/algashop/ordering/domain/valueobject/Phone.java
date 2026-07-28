package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.Objects;

public record Phone(String value) {

    public Phone(String value) {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL);
        if (value.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
