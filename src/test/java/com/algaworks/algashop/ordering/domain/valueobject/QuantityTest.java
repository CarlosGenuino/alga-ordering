package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void deveCriarQuantityComValorValido() {
        Quantity q = new Quantity(5);
        assertEquals("5", q.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(null));
    }

    @Test
    void deveRejeitarValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    void deveExporConstanteZero() {
        assertEquals("0", Quantity.ZERO.toString());
    }

    @Test
    void deveSomarQuantidades() {
        Quantity q1 = new Quantity(3);
        Quantity q2 = new Quantity(2);
        Quantity resultado = q1.add(q2);
        assertEquals("5", resultado.toString());
    }

    @Test
    void deveCompararQuantidades() {
        Quantity q1 = new Quantity(3);
        Quantity q2 = new Quantity(5);
        assertTrue(q1.compareTo(q2) < 0);
        assertTrue(q2.compareTo(q1) > 0);
        assertEquals(0, q1.compareTo(new Quantity(3)));
    }
}
