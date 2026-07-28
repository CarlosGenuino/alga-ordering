package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.Objects;

public record ProductName(String value) {

    public ProductName {
        if (value == null) {
            throw new IllegalArgumentException("O valor não pode ser nulo.");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("O valor não pode estar em branco.");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
