package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.Objects;

public record Quantity(Integer value) implements Comparable<Quantity> {

    public static final Quantity ZERO = new Quantity(0);

    public Quantity {
        if (value == null) {
            throw new IllegalArgumentException("Quantity não pode ser nulo.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Quantity não pode ser negativo.");
        }
    }

    public Quantity add(Quantity other) {
        Objects.requireNonNull(other, "Quantity não pode ser nulo.");
        return new Quantity(this.value + other.value);
    }

    @Override
    public int compareTo(Quantity other) {
        Objects.requireNonNull(other, "Quantity não pode ser nulo.");
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
