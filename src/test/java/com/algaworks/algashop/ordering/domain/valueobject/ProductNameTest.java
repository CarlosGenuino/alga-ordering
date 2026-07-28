package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNameTest {

    @Test
    void deveCriarProductNameComValorValido() {
        ProductName name = new ProductName("Notebook Dell");
        assertEquals("Notebook Dell", name.toString());
    }

    @Test
    void deveRejeitarValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(null));
    }

    @Test
    void deveRejeitarValorEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName("   "));
        assertThrows(IllegalArgumentException.class, () -> new ProductName(""));
    }

    @Test
    void toStringDeveRetornarValorDiretamente() {
        ProductName name = new ProductName("Smartphone Samsung");
        assertEquals("Smartphone Samsung", name.toString());
    }
}
