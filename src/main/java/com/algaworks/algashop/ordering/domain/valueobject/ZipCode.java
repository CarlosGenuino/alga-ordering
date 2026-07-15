package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record ZipCode(String value) {
    public ZipCode(String value) {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_ZIP_CODE_INVALID);
        this.value = value;
    }
}
