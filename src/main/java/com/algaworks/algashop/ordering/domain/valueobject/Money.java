package com.algaworks.algashop.ordering.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal value) implements Comparable<Money> {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        if (value == null) {
            throw new IllegalArgumentException("O valor não pode ser nulo.");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo.");
        }
        value = value.setScale(SCALE, ROUNDING_MODE);
    }

    public Money(String value) {
        this(new BigDecimal(Objects.requireNonNull(value, "Valor não pode ser nulo.")));
    }

    public Money multiply(Quantity quantity) {
        Objects.requireNonNull(quantity, "Quantity não pode ser nulo.");
        if (quantity.value() < 1) {
            throw new IllegalArgumentException("Quantity deve ser >= 1.");
        }
        return new Money(this.value.multiply(BigDecimal.valueOf(quantity.value())));
    }

    public Money add(Money other) {
        Objects.requireNonNull(other, "Money não pode ser nulo.");
        return new Money(this.value.add(other.value));
    }

    public Money divide(Money other) {
        Objects.requireNonNull(other, "Money não pode ser nulo.");
        if (other.value.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return new Money(this.value.divide(other.value, SCALE, ROUNDING_MODE));
    }

    @Override
    public int compareTo(Money other) {
        Objects.requireNonNull(other, "Money não pode ser nulo.");
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
